import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  title = 'Carpark';
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
  ];

}
