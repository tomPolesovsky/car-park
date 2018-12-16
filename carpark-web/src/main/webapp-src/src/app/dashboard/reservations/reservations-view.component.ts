import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ReservationsService} from "../../shared/services/reservations.service";
import {Reservation} from "../../shared/models/reservation.model";
import {ReservationStatus} from "../../shared/models/reservation-status.enum";
import {FilterParams} from "../../shared/models/filter-params.model";
import {LOCAL_FORMAT, PAGE_SIZE} from "../../shared/utils";
import {Moment} from "moment";
import * as moment from "moment";
import {Observable} from "rxjs";
import {PageEvent} from "@angular/material";
import {Roles} from "../../shared/models/roles.enum";

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations-view.component.html',
  styleUrls: ['./reservations-view.component.scss']
})
export class ReservationsViewComponent implements OnInit {

  // reservations: Reservation[] = [];

  filter: FilterParams = {
    page: 1,
    pageSize: PAGE_SIZE,
    query: null,
    from: null,
    to: null,
  };

  // filteredReservation$: Observable<Reservation[]> = this.reservationService.getFilteredReservations(this.filter);
  reservations$: Observable<Reservation[]> = this.reservationService.getReservations();
  // reservations$: Observable<Reservation[]> = this.reservationService.getFilteredReservations(this.filter);
  actualPage = 1;
  userRoles = Roles;
  currentUserRole = JSON.parse(localStorage.getItem('currentUser')).role;

  constructor(private readonly router: Router,
              private readonly reservationService: ReservationsService) {
  }

  ngOnInit() {
    this.reservations$
      .subscribe(data => {
        console.log(data);
        // this.reservations = data;
      });
  }

  formatDate(date: Moment): string {
    return moment(date).format('DD.MM.YYYY');
  }

  // get pages(): number[] {
  //   return new Array(Math.ceil(this.reservations.length / 10)).fill(0);
  // }

  pageChanged(event: PageEvent): void {
    this.filter = {
      ...this.filter,
      page: event.pageIndex + 1,
      pageSize: event.pageSize,
    };
    console.log(this.filter);
    this.filterReservations();
  }

  updateFrom(event): void {
    console.log(event.from);
    this.filter = {
      ...this.filter,
      from:  moment(event.value).format(LOCAL_FORMAT)
    };
    console.log(this.filter);
    this.filterReservations();
  }

  updateTo(event): void {
    this.filter = {
      ...this.filter,
      to:  moment(event.value).format(LOCAL_FORMAT)
    };
    this.filterReservations();
  }

  createReservation(): void {
    this.router.navigateByUrl('/dashboard/new-reservation');
  }

  editReservation(reservation: Reservation): void {
    this.router.navigate(['/dashboard/new-reservation'], {queryParams: {edit: reservation.id}});
  }

  deleteReservation(reservation: Reservation): void {
    this.reservationService.deleteReservation(reservation)
      .subscribe(() => {
        this.reservations$ = this.reservationService.getReservations();
      });
  }

  filterReservations(): void {
    console.log(this.filter);
    this.reservations$ = this.reservationService.getFilteredReservations(this.filter);
  }
}
