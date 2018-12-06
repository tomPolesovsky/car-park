import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Vehicle} from "../models/vehicle.model";

const httpOptions = {
  headers: new HttpHeaders(({'Content-Type': 'application/json'}))
};

@Injectable()
export class VehiclesService {

  constructor(private readonly http: HttpClient) {
  }

  private vehiclesUrl = '/vehicles';

  getVehicles() {
    return this.http.get<Vehicle[]>(this.vehiclesUrl);
  }

  deleteVehicle(vehicle: Vehicle) {
    return this.http.delete(this.vehiclesUrl + '/' + vehicle.id);
  }

  createVehicle(vehicle: Vehicle) {
    return this.http.post<Vehicle>(this.vehiclesUrl, vehicle);
  }

}
