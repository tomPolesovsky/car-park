import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {VehiclesService} from "../../../shared/services/vehicles.service";
import {Vehicle} from "../../../shared/models/vehicle.model";
import {ActivatedRoute, Router} from "@angular/router";
import {Reservation} from "../../../shared/models/reservation.model";
import {ReservationStatus} from "../../../shared/models/reservation-status.enum";
import {EmployeesService} from "../../../shared/services/employees.service";
import {Employee} from "../../../shared/models/employee.model";
import {ReservationsService} from "../../../shared/services/reservations.service";
import {enumToArray, LOCAL_FORMAT, touchAllChildren} from "../../../shared/utils";
import * as moment from "moment";

const VEHICLES_MOCK = [
  {
    registrationNumber: 'ABC123',
    brand: 'Skoda Octavia',
    type: 'Combi',
    color: 'white',
    mileage: 100000,
  },
  {
    registrationNumber: '123ABC',
    brand: 'VW Golf',
    type: 'Hatchback',
    color: 'black',
    mileage: 80000,
  },
  {
    registrationNumber: 'A1B2C3',
    brand: 'Mercedes Vito',
    type: 'Van',
    color: 'gray',
    mileage: 50000,
  },
];

@Component({
  selector: 'app-new-reservation',
  templateUrl: './new-reservation.component.html',
  styleUrls: ['./new-reservation.component.scss']
})
export class NewReservationComponent implements OnInit {

  reservation: Reservation;
  reservationId = '';
  employeeId = '';
  vehicleId = '';
  editMode = false;
  // vehicles = VEHICLES_MOCK;
  vehicles: Vehicle[] = [];
  employees: Employee[] = [];
  warningText = 'This is required!';

  reservationForm = this.fb.group({
    id: '',
    from: ['', Validators.required],
    to: ['', Validators.required],
    employee: ['', Validators.required],
    vehicle: ['', Validators.required],
    status: 'NEW',
  });

  statusOptions = enumToArray(ReservationStatus);

  constructor(private readonly fb: FormBuilder,
              private readonly router: Router,
              private readonly route: ActivatedRoute,
              private readonly vehiclesService: VehiclesService,
              private readonly employeeService: EmployeesService,
              private readonly reservationService: ReservationsService) {
    this.route.queryParams
      .subscribe(params => {
        this.reservationId = params.edit;
        if (this.reservationId) {
          this.editMode = true;
          this.reservationService.getReservations()
            .subscribe(data => {
              this.reservation = data.find(reservation => String(this.reservationId) === String(reservation.id));
              this.initReservationForm();
            });
        }
      });
  }

  ngOnInit(): void {
    this.vehiclesService.getVehicles()
      .subscribe(vehicles => {
        this.vehicles = vehicles;
      });
    this.employeeService.getEmployees()
      .subscribe(employees => {
        this.employees = employees;
      });
  }

  createReservation(): void {
    if (this.reservationForm.valid) {
      this.reservationService.createReservation({
        ...this.reservationForm.value,
        from: moment(this.reservationForm.get('from').value).format(LOCAL_FORMAT),
        to: moment(this.reservationForm.get('to').value).format(LOCAL_FORMAT),
        vehicle: this.vehicles.find(vehicle =>
          String(vehicle.id) === String(this.reservationForm.get('vehicle').value)),
        employee: this.employees.find(employee =>
          String(employee.id) === String(this.reservationForm.get('employee').value)),
      })
        .subscribe(() => this.router.navigateByUrl('/dashboard/reservations'));
    } else {
      touchAllChildren(this.reservationForm);
    }
  }

  initReservationForm(): void {
    this.employeeId = this.reservation.employee.id;
    this.vehicleId = this.reservation.vehicle.id;
    this.reservationForm.patchValue({
      id: this.reservation.id,
      from: this.reservation.from,
      to: this.reservation.to,
      employee: this.reservation.employee.id,
      vehicle: this.reservation.vehicle.id,
      status: this.reservation.status,
    });
    console.log(this.reservationForm.value);
  }

  editReservation(): void {
    if (this.reservationForm.valid) {
      if (JSON.stringify(Object.values(this.reservation)) !== JSON.stringify(Object.values(this.reservationForm.value))) {
        this.reservationService.updateReservation({
          id: this.reservationForm.get('id').value,
          from: moment(this.reservationForm.get('from').value).format(LOCAL_FORMAT),
          to: moment(this.reservationForm.get('to').value).format(LOCAL_FORMAT),
          employee: this.employees.find(employee =>
            String(employee.id) === String(this.reservationForm.get('employee').value)),
          vehicle: this.vehicles.find(vehicle =>
            String(vehicle.id) === String(this.reservationForm.get('vehicle').value)),
          status: this.reservationForm.get('status').value,
        }).subscribe((data) => {
          if (data) {
            console.log('put', data);
            this.router.navigateByUrl('/dashboard/reservations');
          }
        });
      }
      this.router.navigateByUrl('/dashboard/reservations');
    } else {
      touchAllChildren(this.reservationForm);
    }
  }
}
