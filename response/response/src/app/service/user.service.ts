import { Injectable } from '@angular/core';
import { User } from "../model/user";
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class UserService {
    constructor(private http:HttpClient) { } 
httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
};


getUser( fill:string){
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    let fillerValue = "filler="+fill;
    return this.http.post<User[]>(`http://localhost:8089/xhr_post/Index/Login`,fillerValue,{headers:headers});
    }
}