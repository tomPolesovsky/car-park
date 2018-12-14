import {Component, OnInit} from '@angular/core';
import { FormBuilder, Validators } from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../shared/services/authentication.service";
import {first} from "rxjs/operators";
import {User} from "../shared/models/user.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  warningText = 'This field is required!';
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  loggedUser: User;


  loginForm = this.fb.group({
    userName: ['', Validators.required],
    password: ['', Validators.required],
  });

  constructor(private readonly fb: FormBuilder,
              private readonly route: ActivatedRoute,
              private readonly router: Router,
              private readonly authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    // reset login status
    this.authenticationService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit(): void {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.loginForm.get('userName').value, this.loginForm.get('password').value)
      .pipe(first())
      .subscribe(
        data => {
          this.loggedUser = data;
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.error = error;
          this.loading = false;
        });
  }

  redirectToDashboard(): void {
    this.router.navigateByUrl('/dashboard/reservations');
  }
}

