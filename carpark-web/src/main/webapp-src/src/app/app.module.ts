import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from "./app-routing.module";
import { DashboardComponent } from './dashboard/dashboard.component';
import {LoginComponent} from "./landing/login.component";
import {ReservationsViewComponent} from "./dashboard/reservations/reservations-view.component";
import {EmployeesViewComponent} from "./dashboard/employees/employees-view.component";
import {VehiclesViewComponent} from "./dashboard/vehicles/vehicles-view.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {EmployeesService} from "./shared/services/employees.service";
import {VehiclesService} from "./shared/services/vehicles.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ReservationsService} from "./shared/services/reservations.service";
import {AuthenticationService} from "./shared/services/authentication.service";
import {JwtInterceptor} from "./shared/helpers/jwt.interceptor";
import {ErrorInterceptor} from "./shared/helpers/error.interceptor";
import { SettingsComponent } from './dashboard/settings/settings.component';
import { NewReservationComponent } from './dashboard/reservations/new-reservation/new-reservation.component';
import {MaterialModule} from "./shared/modules/material.module";
import {SeparateThousandsPipe} from "./shared/pipes/separate-thousands.pipe";
import {ReservationSettingsService} from "./shared/services/reservation-settings.service";
import { NewEmployeeComponent } from './dashboard/employees/new-employee/new-employee.component';
import { NewVehicleComponent } from './dashboard/vehicles/new-vehicle/new-vehicle.component';
import { NewSettingComponent } from './dashboard/settings/new-setting/new-setting.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ReservationsViewComponent,
    VehiclesViewComponent,
    EmployeesViewComponent,
    SettingsComponent,
    NewReservationComponent,
    NewEmployeeComponent,
    NewVehicleComponent,
    NewSettingComponent,

    SeparateThousandsPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
    BrowserAnimationsModule,
  ],
  exports: [
    SeparateThousandsPipe,
  ],
  providers: [
    VehiclesService,
    EmployeesService,
    ReservationsService,
    AuthenticationService,
    ReservationSettingsService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
