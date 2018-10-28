import { User_Classes } from './../models/user-classes.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


@Injectable()
export class User_Classes_Service {
  constructor(private http: HttpClient){}
  httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  sendSubject(selectedVal: string){
    //console.log(usr_classes);
    let body = new HttpParams();
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');

    body = body.set('subject', selectedVal);
    
   return this.http.post(`http://localhost:8089/xhr_post/UserClasses`, body, {headers: headers});
  }

}