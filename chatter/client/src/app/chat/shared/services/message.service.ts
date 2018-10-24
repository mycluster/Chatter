import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Message } from './../model/message';
import { User } from './../model/user';
import { MessageInter } from './../model/message_interface';
import { Time } from '@angular/common';
@Injectable({
  providedIn: 'root'
})

export class MessageService {

  constructor(private http:HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  };

  selectAllMessage() {
    return this.http.get<Message[]>(http://localhost:8085/Chatter/selectAllMessage)
  }

  selectMostRecentMessage() {
    return this.http.get<Message[]>(http://localhost:8085/Chatter/selectMostRecentMessage)
  }

  insertMessage(id:string, message: string, sender:string, receiver:string, sentAt: Time) {
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    body = body.set('id', id);
    body = body.set('message', message);
    body = body.set('sender', sender);
    body =body.set('receiver', receiver);
    body=body.set('sentAt', sentAt)

    return this.http.post("http:localhost:8085/Chatter/insertMessage", 
    body, 
    {headers:headers});
  }


  
  
  deleteMessageById(id:string, message: string, sender:string, receiver:string, sentAt: Time) {
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    body = body.set('message', null);
    body = body.set('sender', null);
    body =body.set('receiver', null);
    body=body.set('sentAt', null)
  }

  updateNote(id:string, message: string, sender:string, receiver:string, sentAt: Time){   //do we want to include messages with the User class, then we could update a note  
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    body = body.set('id', id);
    body = body.set('message', message);
    body = body.set('sender', sender);
    body =body.set('receiver', receiver);
    body=body.set('sentAt', sentAt)
  }

}
