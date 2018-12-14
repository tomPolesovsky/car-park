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
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {VehiclesService} from "./shared/services/vehicles.service";
import {EmployeesService} from "./shared/services/employees.service";
import {ReservationsService} from "./shared/services/reservations.service";
import {JwtInterceptor} from "./shared/helpers/jwt.interceptor";
import {ErrorInterceptor} from "./shared/helpers/error.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ReservationsViewComponent,
    VehiclesViewComponent,
    EmployeesViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    VehiclesService,
    EmployeesService,
    ReservationsService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
