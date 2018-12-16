import { Component, OnInit } from '@angular/core';
import {ReservationSettings} from "../../shared/models/reservation-settings.model";
import {ReservationSettingsService} from "../../shared/services/reservation-settings.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit {

  settings$: Observable<ReservationSettings[]> = this.reservationSettingsService.getReservationSettings();

  constructor(private readonly reservationSettingsService: ReservationSettingsService,
              private readonly router: Router) {
  }

  ngOnInit() {
    this.settings$
      .subscribe(() => {});
  }

  addSettings(): void {
    this.router.navigateByUrl('/dashboard/new-setting');
  }

  editSettings(settings: ReservationSettings): void {
    this.router.navigate(['/dashboard/new-setting'], { queryParams: { edit: settings.id } });
  }

  deleteSettings(settings: ReservationSettings): void {
    this.reservationSettingsService.deleteReservationSettings(settings)
      .subscribe(() => {
        this.settings$ = this.reservationSettingsService.getReservationSettings();
      });
  }
}
