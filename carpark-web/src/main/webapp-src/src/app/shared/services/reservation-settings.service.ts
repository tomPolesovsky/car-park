import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {ReservationSettings} from "../models/reservation-settings.model";
import {requestPath} from "../utils";
import {Reservation} from "../models/reservation.model";
import {Employee} from "../models/employee.model";

const httpOptions = {
  headers: new HttpHeaders(({'Content-Type': 'application/json'}))
};

@Injectable()
export class ReservationSettingsService {

  private reservationSettingsUrl = '/reservation-settings';
  private findbyEmployeeUrl = '/find-by-employee';

  constructor(private readonly http: HttpClient) {
  }

  getReservationSettings() {
    return this.http.get<ReservationSettings[]>(`${requestPath}${this.reservationSettingsUrl}`);
  }

  deleteReservationSettings(reservationSettings: ReservationSettings) {
    return this.http.delete(`${requestPath}${this.reservationSettingsUrl}/${reservationSettings.id}`);
  }

  createReservationSettings(reservationSettings: ReservationSettings) {
    return this.http.post<ReservationSettings>(`${requestPath}${this.reservationSettingsUrl}`, reservationSettings);
  }

  updateReservationSettings(reservationSettings: ReservationSettings) {
    return this.http.put<Reservation>(`${requestPath}${this.reservationSettingsUrl}`, reservationSettings);
  }

  getReservationSettingsByEmployee(employee: Employee) {
    return this.http.post<ReservationSettings>(`${requestPath}${this.reservationSettingsUrl}${this.findbyEmployeeUrl}`, employee);
  }
}
