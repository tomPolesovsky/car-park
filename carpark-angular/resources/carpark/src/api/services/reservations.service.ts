import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Reservation} from "../models/reservation.model";

const httpOptions = {
  headers: new HttpHeaders(({'Content-Type': 'application/json'}))
};

@Injectable()
export class ReservationsService {

  constructor(private readonly http: HttpClient) {
  }

  private reservationsUrl = '/reservations';

  getReservations() {
    return this.http.get<Reservation[]>(this.reservationsUrl);
  }

  deleteReservation(reservation: Reservation) {
    return this.http.delete(this.reservationsUrl + '/' + reservation.id);
  }

  createReservation(reservation: Reservation) {
    return this.http.post<Reservation>(this.reservationsUrl, reservation);
  }

}
