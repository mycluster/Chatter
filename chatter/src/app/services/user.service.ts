import { Injectable } from '@angular/core';
import { User } from "../models/user";
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

@Injectable()
export class UserService {
    constructor(private http:HttpClient) { } 
httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
};

login(user: User) {
    let body = new HttpParams();
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
   // console.log(user.username);
    let bod = 'username='+user.username;
  //  body = body.set('username', user.username);
    console.log(bod);
    return this.http.post(`http://localhost:8089/xhr_post/Index/Login`,bod,{headers:headers});
}

  
}