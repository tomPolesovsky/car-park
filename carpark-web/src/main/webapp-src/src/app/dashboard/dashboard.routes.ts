import {Routes} from "@angular/router";
import {VehiclesViewComponent} from "./vehicles/vehicles-view.component";
import {ReservationsViewComponent} from "./reservations/reservations-view.component";
import {EmployeesViewComponent} from "./employees/employees-view.component";

export const dashboardRoutes: Routes = [{
  path: '',
  children: [
    {path: 'vehicles', component: VehiclesViewComponent},
    {path: 'reservations', component: ReservationsViewComponent},
    {path: 'employees', component: EmployeesViewComponent},
  ]
}];
