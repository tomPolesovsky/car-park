import {Routes} from "@angular/router";
import {VehiclesViewComponent} from "./vehicles/vehicles-view.component";
import {ReservationsViewComponent} from "./reservations/reservations-view.component";
import {EmployeesViewComponent} from "./employees/employees-view.component";
import {SettingsComponent} from "./settings/settings.component";
import {NewReservationComponent} from "./reservations/new-reservation/new-reservation.component";
import {NewEmployeeComponent} from "./employees/new-employee/new-employee.component";
import {NewVehicleComponent} from "./vehicles/new-vehicle/new-vehicle.component";
import {NewSettingComponent} from "./settings/new-setting/new-setting.component";


export const dashboardRoutes: Routes = [{
  path: '',
  children: [
    {path: 'vehicles', component: VehiclesViewComponent},
    {path: 'reservations', component: ReservationsViewComponent},
    {path: 'employees', component: EmployeesViewComponent},
    {path: 'settings', component: SettingsComponent},
    {path: 'new-reservation', component: NewReservationComponent},
    {path: 'new-employee', component: NewEmployeeComponent},
    {path: 'new-vehicle', component: NewVehicleComponent},
    {path: 'new-setting', component: NewSettingComponent},
  ]
}];
