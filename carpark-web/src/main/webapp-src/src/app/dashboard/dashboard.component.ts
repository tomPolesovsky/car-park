import { Component } from '@angular/core';
import {AuthenticationService} from "../shared/services/authentication.service";
import {Router} from "@angular/router";
import {Roles} from "../shared/models/roles.enum";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  title = 'Carpark';
  currentUserRole = JSON.parse(localStorage.getItem('currentUser')).role;
  userRoles = Roles;
  navigationItems: { name: string, route: string }[] = [
    {
      name: 'Employees',
      route: 'employees',
    },
    {
      name: 'Vehicles',
      route: 'vehicles',
    },
    {
      name: 'Reservations',
      route: 'reservations',
    },
    {
      name: 'Settings',
      route: 'settings',
    }
  ];

  constructor(private readonly authenticationService: AuthenticationService,
              private readonly router: Router) {
  }

  logout(): void {
    this.router.navigateByUrl('/login');
    // this.authenticationService.logout();
  }

}
