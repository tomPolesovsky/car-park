import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Vehicle} from "../models/vehicle.model";
import {requestPath} from "../utils";

@Injectable()
export class VehiclesService {

  private vehiclesUrl = '/vehicles';

  constructor(private readonly http: HttpClient) {
  }

  getVehicles() {
    return this.http.get<Vehicle[]>(`${requestPath}${this.vehiclesUrl}`);
  }

  deleteVehicle(vehicle: Vehicle) {
    return this.http.delete(`${requestPath}${this.vehiclesUrl}/${vehicle.id}`);
  }

  createVehicle(vehicle: Vehicle) {
    return this.http.post<Vehicle>(`${requestPath}${this.vehiclesUrl}`, vehicle);
  }

  updateVehicle(vehicle: Vehicle) {
    return this.http.put<Vehicle>(`${requestPath}${this.vehiclesUrl}`, vehicle);
  }

  findVehicleById(id: string) {
    return this.http.get<Vehicle>(`${requestPath}${this.vehiclesUrl}/${id}`);
  }
}
