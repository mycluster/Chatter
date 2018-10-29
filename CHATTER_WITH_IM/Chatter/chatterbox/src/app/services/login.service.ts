import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginModel } from '../models/login.model';
import { CheckPasswordModel } from './../models/response-models/check-password.model';

@Injectable()
export class LoginService {
  constructor(private http:HttpClient) { }
  httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  login(credentials: LoginModel){
    let body = new HttpParams();
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    
    body = body.set('username', credentials.username);
    body = body.set('password', credentials.password);

    return this.http.post<CheckPasswordModel[]>(`http://localhost:8089/chatterJava/Login`,body,{headers:headers});
  }
};
