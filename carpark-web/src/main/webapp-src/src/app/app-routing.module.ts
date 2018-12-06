import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./landing/login.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {dashboardRoutes} from "./dashboard/dashboard.routes";

const routes: Routes = [{
  path: '',
  children: [
    {path: 'login', component: LoginComponent},
    {path: 'dashboard', component: DashboardComponent, children: [...dashboardRoutes]},
    {path: '**', redirectTo: 'login'},
  ],
}];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
