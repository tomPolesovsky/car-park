import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
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
  private acceptOrDeclineUrl = '/accept-or-decline';

  reservations$ = new Subject<Reservation[]>();

  constructor(private readonly http: HttpClient) {
  }

  getReservations() {
    return this.http.get<Reservation[]>(`${requestPath}${this.reservationsUrl}`);
  }

  deleteReservation(reservation: Reservation) {

    return this.http.delete(`${requestPath}${this.reservationsUrl}/${reservation.id}`);
  }

  createReservation(reservation: Reservation) {
    return this.http.post<Reservation>(`${requestPath}${this.reservationsUrl}${this.processRequest}`, reservation);
  }

  updateReservation(reservation: Reservation) {
    return this.http.put<Reservation>(`${requestPath}${this.reservationsUrl}`, reservation);
  }

  getFilteredReservations(filterParams: FilterParams) {
    return this.http.post<Reservation[]>(`${requestPath}${this.reservationsUrl}${this.filterUrl}`, filterParams);
  }

  acceptOrDeclineReservation(reservation: Reservation, toBeAccepted: boolean) {
    return this.http.put<Reservation>(`${requestPath}${this.reservationsUrl}${this.acceptOrDeclineUrl}`,
      reservation, {
      params: {
        'toBeAccepted': String(toBeAccepted)
      }
    });
  }
}
