import {Component, OnInit} from '@angular/core';
import {Vehicle} from "../../shared/models/vehicle.model";
import {VehiclesService} from "../../shared/services/vehicles.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {Roles} from "../../shared/models/roles.enum";

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles-view.component.html',
  styleUrls: ['./vehicles-view.component.scss']
})
export class VehiclesViewComponent implements OnInit {

  vehicles$: Observable<Vehicle[]> = this.vehicleService.getVehicles();
  userRoles = Roles;
  currentUserRole = JSON.parse(localStorage.getItem('currentUser')).role;

    constructor(private readonly vehicleService: VehiclesService,
              private readonly router: Router) {
  }

  ngOnInit(): void {
    this.vehicles$
      .subscribe(() => {});
  }

  addVehicle(): void {
    this.router.navigateByUrl('/dashboard/new-vehicle');
  }

  editVehicle(vehicle: Vehicle): void {
    this.router.navigate(['/dashboard/new-vehicle'], {queryParams: {edit: vehicle.id}});
  }

  deleteVehicle(vehicle: Vehicle): void {
    this.vehicleService.deleteVehicle(vehicle)
      .subscribe(() => {
        this.vehicles$ = this.vehicleService.getVehicles();
      },
        error =>
          alert('You don\'t have permission to remove this vehicle!')
      );
  }
}
