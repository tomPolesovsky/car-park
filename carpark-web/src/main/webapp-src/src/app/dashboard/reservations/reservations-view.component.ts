import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ReservationsService} from "../../shared/services/reservations.service";
import {Reservation} from "../../shared/models/reservation.model";
import {ReservationStatus} from "../../shared/models/reservation-status.enum";
import {FilterParams} from "../../shared/models/filter-params.model";
import {LOCAL_FORMAT, PAGE_SIZE} from "../../shared/utils";
import * as moment from "moment";
import {Moment} from "moment";
import {Observable} from "rxjs";
import {MatInput, PageEvent} from "@angular/material";
import {Roles} from "../../shared/models/roles.enum";

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations-view.component.html',
  styleUrls: ['./reservations-view.component.scss']
})
export class ReservationsViewComponent implements OnInit {

  @ViewChild('fromInput', {
    read: MatInput
  }) fromInput: MatInput;

  @ViewChild('toInput', {
    read: MatInput
  }) toInput: MatInput;

  reservations: Reservation[] = [];

  filter: FilterParams = {
    page: 1,
    page_size: PAGE_SIZE,
    query: '',
    from: null,
    to: null
  };

  reservations$: Observable<Reservation[]> = this.reservationService.getFilteredReservations(this.filter);
  userRoles = Roles;
  currentUser = JSON.parse(localStorage.getItem('currentUser'));

  constructor(private readonly router: Router,
              private readonly route: ActivatedRoute,
              private readonly reservationService: ReservationsService) {
  }

  ngOnInit() {
    this.reservations$
      .subscribe(data => {
        if (data !== null && data.length > 0) {
          this.route.queryParams
            .subscribe(params => {
              if (params.id) {
                const approved = data.some(reservation =>
                  String(reservation.id) === String(params.id) && reservation.status === ReservationStatus.APPROVED);
                if (approved) {
                  alert('Your reservation is approved now!');
                }
              }
            });
        }
      });
  }

  acceptOrDeclineReservation(reservation: Reservation, toBeAccepted: boolean): void {
    if (reservation.status === ReservationStatus.NEW) {
      this.reservationService.acceptOrDeclineReservation(reservation, toBeAccepted)
        .subscribe(() => {
            this.reservations.map(res => (
              String(res.id === reservation.id)
                ? {
                  ...res,
                  status: toBeAccepted ? ReservationStatus.APPROVED : ReservationStatus.CANCELED
                }
                : res));
            this.reservations$ = this.reservationService.getFilteredReservations(this.filter);
        }
        );
    } else {
      alert('This reservation has already set the status!');
    }

  }

  resetFilter(): void {
    this.fromInput.value = '';
    this.toInput.value = '';
    this.filter = {
      page: 1,
      page_size: PAGE_SIZE,
      query: '',
      from: null,
      to: null
    };
    this.reservations$ = this.reservationService.getFilteredReservations(this.filter);
  }

  formatDate(date: Moment): string {
    return moment(date).format('DD.MM.YYYY');
  }

  pageChanged(event: PageEvent): void {
    this.filter = {
      ...this.filter,
      page: event.pageIndex + 1,
      page_size: event.pageSize,
    };
    this.filterReservations();
  }

  updateFrom(event): void {
    this.filter = {
      ...this.filter,
      from: moment(event.value).format(LOCAL_FORMAT)
    };
    this.filterReservations();
  }

  updateTo(event): void {
    this.filter = {
      ...this.filter,
      to: moment(event.value).format(LOCAL_FORMAT)
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
        this.reservations$ = this.reservationService.getFilteredReservations(this.filter);
      });
  }

  filterReservations(): void {
    this.reservations$ = this.reservationService.getFilteredReservations(this.filter);
  }
}
