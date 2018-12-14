import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Reservation} from "../models/reservation.model";
import {requestPath} from "../utils";
import {FilterParams} from "../models/filter-params.model";

@Injectable()
export class ReservationsService {

  private reservationsUrl = '/reservations';
  private filterUrl = '/filter';

  constructor(private readonly http: HttpClient) {
  }

  getReservations() {
    return this.http.get<Reservation[]>(`${requestPath}${this.reservationsUrl}`);
  }

  deleteReservation(reservation: Reservation) {
    return this.http.delete(`${requestPath}${this.reservationsUrl}/${reservation.id}`);
  }

  createReservation(reservation: Reservation) {
    return this.http.post<Reservation>(`${requestPath}${this.reservationsUrl}`, reservation);
  }

  updateReservation(reservation: Reservation) {
    return this.http.put<Reservation>(`${requestPath}${this.reservationsUrl}`, reservation);
  }

  getFilteredReservations(filterParams: FilterParams) {
    return this.http.get<Reservation[]>(`${requestPath}${this.filterUrl}/${filterParams}`);
  }
}
