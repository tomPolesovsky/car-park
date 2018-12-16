import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Reservation} from "../models/reservation.model";
import {requestPath} from "../utils";
import {FilterParams} from "../models/filter-params.model";
import {AuthenticationService} from "./authentication.service";
import {Observable, Subject} from "rxjs";

@Injectable()
export class ReservationsService {

  private reservationsUrl = '/reservations';
  private processRequest = '/process-request';
  private filterUrl = '/filter';

  reservations$ = new Subject<Reservation[]>();

  constructor(private readonly http: HttpClient) {
  }

  getReservations() {
    const got = this.http.get<Reservation[]>(`${requestPath}${this.reservationsUrl}`);
    got.subscribe(data => this.reservations$.next(data));
    return got;
    // return this.http.get<Reservation[]>(`${requestPath}${this.reservationsUrl}`);
  }

  deleteReservation(reservation: Reservation) {
    // const data = this.http.delete(`${requestPath}${this.reservationsUrl}/${reservation.id}`);
    //   data.subscribe(data => console.log(data));
    // return data;
    return this.http.delete(`${requestPath}${this.reservationsUrl}/${reservation.id}`);
  }

  createReservation(reservation: Reservation) {
    return this.http.post<Reservation>(`${requestPath}${this.reservationsUrl}${this.processRequest}`, reservation);
  }

  updateReservation(reservation: Reservation) {
    return this.http.put<Reservation>(`${requestPath}${this.reservationsUrl}`, reservation);
  }

  getFilteredReservations(filterParams: FilterParams) {
    console.log(filterParams);
    return this.http.post<Reservation[]>(`${requestPath}${this.reservationsUrl}${this.filterUrl}`, filterParams);
  }
}
