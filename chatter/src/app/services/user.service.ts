import { Injectable } from '@angular/core';
import { User } from "../models/user";
import { HttpClient, HttpHeaders, HttpParams, XhrFactory} from '@angular/common/http';
import {IUser} from '../models/getUser';
import {Observable} from 'rxjs';

@Injectable()
export class UserService {
    constructor(private http:HttpClient) { } 
httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
};

login(user: User){
   let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
   // console.log(user.username);
    let bod = 'username='+user.username;
    //body = body.set('username', user.username);
    console.log(bod);
    return this.http.post(`http://localhost:8089/xhr_post/Index/Login`,bod,{headers:headers});
}

getUser( iuser:IUser): Observable<IUser[]>{
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    let fillerValue = "filler=blah";
    return this.http.post<IUser[]>(`http://localhost:8089/xhr_post/Index/Login`,fillerValue,{headers:headers});
}

  
}