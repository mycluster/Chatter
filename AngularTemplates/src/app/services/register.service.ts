import { RegisterModel } from './../models/register.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


@Injectable()
export class RegisterService {
  constructor(private http: HttpClient){}
  httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  register(registration: RegisterModel){
    let body = new HttpParams();
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');

    body = body.set('username', registration.username);
    body = body.set('email', registration.password);
    body = body.set('password', registration.username);
    body = body.set('confirm_password', registration.confirm_password);

    return this.http.post(`http://localhost:8085/xhr_post/Register`, body, {headers: headers});

  }
}