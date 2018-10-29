import { RegisterModel } from './../models/register.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


@Injectable()
export class RegisterService {
  constructor(
    private http: HttpClient,
    ){}
  httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  register(registration: RegisterModel){
    let body = new HttpParams();
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    body = body.set('firstname', registration.firstname);
    body = body.set('lastname', registration.lastname);
    body = body.set('username', registration.username);
    body = body.set('email', registration.email);
    body = body.set('password', registration.password);
   

    return this.http.post(`http://localhost:8089/chatterJava/Register`, body, {headers: headers});
  }

}