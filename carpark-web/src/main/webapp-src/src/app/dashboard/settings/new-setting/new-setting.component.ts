import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {VehiclesService} from "../../../shared/services/vehicles.service";
import {EmployeesService} from "../../../shared/services/employees.service";
import {ReservationSettings} from "../../../shared/models/reservation-settings.model";
import {ReservationSettingsService} from "../../../shared/services/reservation-settings.service";
import {Employee} from "../../../shared/models/employee.model";
import {touchAllChildren} from "../../../shared/utils";
import {Observable} from "rxjs";
import {Reservation} from "../../../shared/models/reservation.model";

@Component({
  selector: 'app-new-setting',
  templateUrl: './new-setting.component.html',
  styleUrls: ['./new-setting.component.scss']
})
export class NewSettingComponent implements OnInit {

  warningText = 'This is required!';
  settings: ReservationSettings;
  settingsId = '';
  editMode = false;
  settingsForm = this.fb.group({
    id: '',
    employee: ['', Validators.required],
    allowed: false,
    auto_approval: false,
  });

  employees: Employee[] = [];
  employeeId = '';

  constructor(private readonly fb: FormBuilder,
              private readonly router: Router,
              private readonly route: ActivatedRoute,
              private readonly vehiclesService: VehiclesService,
              private readonly employeeService: EmployeesService,
              private readonly settingsService: ReservationSettingsService) {
    this.route.queryParams
      .subscribe(params => {
        this.settingsId = params.edit;
        if (this.settingsId) {
          this.editMode = true;
          this.settingsService.getReservationSettings()
            .subscribe(data => {
              this.settings = data.find(settings => String(this.settingsId) === String(settings.id));
              this.initSettingsForm();
            });
        }
      });
  }

  ngOnInit(): void {
    this.employeeService.getEmployees()
      .subscribe(employees => {
        this.employees = employees;
      });
  }

  addSettings(): void {
    if (this.settingsForm.valid) {
      this.settingsService.createReservationSettings(this.settingsForm.value)
        .subscribe(() => {
          this.router.navigateByUrl('/dashboard/settings');
        },
          error => alert('Reservation settings for this employee already exist!')
        );
    } else {
      touchAllChildren(this.settingsForm);
    }
  }

  initSettingsForm(): void {
    this.employeeId = this.settings.employee.id;
    this.settingsForm.patchValue({
      id: this.settings.id,
      employee: `${this.settings.employee.first_name} ${this.settings.employee.last_name}`,
      allowed: this.settings.allowed,
      auto_approval: this.settings.auto_approval,
    });
  }

  editSettings(): void {
    if (this.settingsForm.valid) {
      if (JSON.stringify(Object.values(this.settings)) !== JSON.stringify(Object.values(this.settingsForm.value))) {
        this.settingsService.updateReservationSettings({
          ...this.settingsForm.value,
          employee: this.employees.find(employee => String(employee.id) === String(this.employeeId)),
        })
          .subscribe((data) => {
            console.log('edited', data);
            this.router.navigateByUrl('/dashboard/settings');
          });
      }
      this.router.navigateByUrl('/dashboard/settings');
    } else {
      touchAllChildren(this.settingsForm);
    }
  }

}
