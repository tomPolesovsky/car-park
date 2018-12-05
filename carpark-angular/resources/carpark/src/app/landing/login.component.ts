import { Component } from '@angular/core';
import { FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  warningText = 'This field is required!';

  loginForm = this.fb.group({
    userName: ['', Validators.required],
    password: ['', Validators.required],
  });

  constructor(private readonly fb: FormBuilder,
              private readonly router: Router) {
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      if (this.loginForm.get('userName').value === 'admin' && this.loginForm.get('password').value === 'admin') {
        this.redirectToDashboard();
      }
    } else {
      this.loginForm.controls['userName'].markAsTouched();
      this.loginForm.controls['password'].markAsTouched();
    }
  }

  redirectToDashboard(): void {
    this.router.navigateByUrl('/dashboard/reservations');
  }

}
