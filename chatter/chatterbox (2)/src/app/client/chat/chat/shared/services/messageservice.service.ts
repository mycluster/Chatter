import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Message } from './../model/message';
import { User } from './../model/user';
import { Time } from '@angular/common';
@Injectable({
  providedIn: 'root'
})

//Future iterations of ChatterBox will use REST to connect with the backend where messages 
//will be persisted in the database. This is done making HttpClient to communicate.
//This will allow the user to see the time of the message, as well as retrieve 
//and edit messages between two users.
export class MessageService {

  constructor(private http:HttpClient, private messageService: MessageService) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  };


  selectByConversation(username1: string, username2: string) {
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    
    body = body.set('username1', username1);
    body = body.set('username2', username2);

    return this.http.post<Message[]>("http://localhost:4200/Chatter/SelectByConversation", body, {headers:headers});
  }

  selectNMostRecentByConversation(username1: string, username2: string, n: number) {
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    
    body = body.set('username1', username1);
    body = body.set('username2', username2);

    

    return this.http.post<Message[]>("http://localhost:8085/Chatter/SelectNMostRecentConversation", 
    body, 
    {headers:headers});
  }


  insertMessage(id:string, message: string, sender:string, receiver:string, sentAt: Time, edited: boolean) {
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    body = body.set('id', id);
    body = body.set('message', message);
    body = body.set('sender', sender);
    body =body.set('receiver', receiver);
    body=body.set('sentAt', JSON.stringify(sentAt));
    body=body.set('edited', JSON.stringify(edited))

    return this.http.post("http://localhost:4200/Chatter/InsertMessage", 
    body, 
    {headers:headers});
  }


  deleteMessageById(id:string, message: string, sender:string, receiver:string, sentAt: Time) {
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
    body= body.set('id', null);
    body = body.set('message', null);
    body = body.set('sender', null);
    body =body.set('receiver', null);
    body=body.set('sentAt', null)
  }

  updateMessage(id:string, message: string, sender:string, receiver:string, sentAt: Time){   //do we want to include messages with the User class, then we could update a note  
    let body = new HttpParams();
    let headers = new HttpHeaders().set(
      'Content-Type', 'application/x-www-form-urlencoded'
    );
     body = body.set('id', id);
    body = body.set('message', message);
    body = body.set('sender', sender);
    body =body.set('receiver', receiver);
    body=body.set('sentAt', JSON.stringify(sentAt))
  }


}