import { NoteFormFindModel } from './../models/response-models/note-form-find.model';
import { NoteFormModel } from './../models/note-form.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


@Injectable()
export class NoteFormService {
  constructor(private http: HttpClient){}

  httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  sendNote(noteModelo: NoteFormModel){
    let body = new HttpParams();
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
   
    body = body.set('notename', noteModelo.notename);
    body = body.set('note', noteModelo.note);
   return this.http.post(`http://localhost:8089/chatterJava/NoteForm`, body, {headers: headers});
  }
  findNote(notename:string){
    let body = new HttpParams();
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
   
    body = body.set('notename', notename);
    return this.http.post<NoteFormFindModel[]>(`http://localhost:8089/chatterJava/FindNote_Servlet`, body, {headers: headers});
   
  }

}