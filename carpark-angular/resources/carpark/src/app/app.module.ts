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
import {EmployeesService} from "../api/services/employees.service";
import {VehiclesService} from "../api/services/vehicles.service";
import {HttpClientModule} from "@angular/common/http";
import {ReservationsService} from "../api/services/reservations.service";

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
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
