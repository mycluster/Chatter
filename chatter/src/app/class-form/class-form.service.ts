import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Class_Form } from "../models/class-form.model";

@Injectable()
export class Class_Form_Service {
    constructor(private http:HttpClient) { } 
httpOptions={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
};

/*Important to note, that although this method is only posting, it also returns an 
 *object. This method is essentially a life hack to perform what would be the http.get()
 *To make it work i pass in a dummy value, called fillerValue, to connect to the servlet
 *and have it give us a response. Note that this service will have its own servlet which
 *will also be set to take in a dummy value and respond with the data we need.
 *
 *Although sort of looparoundy, this method has the advantage over a normal http.get() in that
 *it can post AND get and not only get.  
 */
sendSubject(subject:string,classForm:Class_Form){
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    let body = new HttpParams();
    
    body = body.set('username', classForm.username);
    body = body.set('classname', classForm.classname);
    body = body.set('subject', subject);
    //in the following step, notice we cast the respond object, to datatype of class_form
    return this.http.post<Class_Form[]>(`http://localhost:8089/xhr_post/ClassForm`,body,{headers:headers});
    }//end sendClass_Form()

}//end class