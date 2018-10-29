import { EditUser } from './../models/edit-user.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable()
export class EditUser_Serv {
  constructor(private http: HttpClient){}
  httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  sendEditUsr(editUsr:EditUser){
    let body = new HttpParams();
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');

    body = body.set('name', editUsr.name);
    body = body.set('major', editUsr.major);
    body = body.set('email', editUsr.email);


    return this.http.post(`http://18.223.161.42:8085/xhr_post/EditUser`, body, {headers: headers});
  }//end register()
}//end class
