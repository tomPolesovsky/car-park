import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {VehiclesService} from "../../../shared/services/vehicles.service";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeesService} from "../../../shared/services/employees.service";
import {touchAllChildren} from "../../../shared/utils";
import {Employee} from "../../../shared/models/employee.model";
import {Vehicle} from "../../../shared/models/vehicle.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-new-vehicle',
  templateUrl: './new-vehicle.component.html',
  styleUrls: ['./new-vehicle.component.scss']
})
export class NewVehicleComponent implements OnInit {

  warningText = 'This is required!';
  vehicleId = '';
  editMode = false;
  vehicle: Vehicle;

  vehicleForm = this.fb.group({
    id: '',
    brand: ['', Validators.required],
    registration_number: ['', Validators.required],
    type: ['', Validators.required],
    color: ['', Validators.required],
    mileage: ['', [Validators.required, Validators.pattern('^[1-9][0-9]*$')]],
  });


  constructor(private readonly fb: FormBuilder,
              private readonly router: Router,
              private readonly route: ActivatedRoute,
              private readonly vehicleService: VehiclesService) {
  }

  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.vehicleId = params.edit;
        if (this.vehicleId) {
          this.editMode = true;
          this.vehicleService.getVehicles()
            .subscribe(data => {
              this.vehicle = data.find(vehicle => String(vehicle.id) === String(this.vehicleId));
              this.initVehicleForm();
            });
        }
      });
  }

  initVehicleForm(): void {
    this.editMode = true;
    this.vehicleForm.setValue(this.vehicle);
  }

  editVehicle(): void {
    if (this.vehicleForm.valid) {
      if (JSON.stringify(Object.values(this.vehicle)) !== JSON.stringify(Object.values(this.vehicleForm.value))) {
        this.vehicleService.updateVehicle({...this.vehicleForm.value})
          .subscribe(() => this.router.navigateByUrl('/dashboard/vehicles'));
      }
      this.router.navigateByUrl('/dashboard/vehicles');
    } else {
      touchAllChildren(this.vehicleForm);
    }
  }

  createVehicle(): void {
    if (this.vehicleForm.valid) {
      this.vehicleService.createVehicle({...this.vehicleForm.value})
        .subscribe(() => this.router.navigateByUrl('/dashboard/vehicles'));
    } else {
      touchAllChildren(this.vehicleForm);
    }
  }
}
