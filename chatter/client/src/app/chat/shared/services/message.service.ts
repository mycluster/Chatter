import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Message } from './../model/message';
import { User } from './../model/user';

@Injectable({
  providedIn: 'root'
})

export class MessageService {

  constructor(private http:HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  };

  selectAllMessage() {
    return this.http.get<Message>
  }

  selectMostRecentMessage() {
    return this.http.get<Message[]>(http://localhost:8085/Chatter/selectMostRecentMessage)
  }

  insertMessage(message: string) {
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    body = body.set('username', username);
    body = body.set('message', message);
  }
  
  deleteMessageById(id: number) {
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    body = body.set('message', null)
  }

  updateNote(id:number){   //do we want to include messages with the User class, then we could update a note  
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    body = body.set('message', message);

  }

}
