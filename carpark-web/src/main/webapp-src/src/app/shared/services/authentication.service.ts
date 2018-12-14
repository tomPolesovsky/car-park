import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from "rxjs";
import { requestPath } from "../utils";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  private loginUrl = '/login';

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${requestPath}${this.loginUrl}`, {username, password}
    )
      .pipe(map(user => {
        // login successful if there's a jwt token in the response
        if (user) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
        }
        return user;
      }));
  }

  logout(): void {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
}
