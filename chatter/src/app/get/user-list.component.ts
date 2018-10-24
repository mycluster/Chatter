import {UserService} from '../services/user.service';
import { Component, OnInit} from '@angular/core';
import { IUser } from '../models/getUser';

@Component({
    selector:'iuser-list',
    template:'user-list.component.html',
    providers:[UserService]
})
export class UserListComponent implements OnInit{
    listuser:IUser[] = [];

    constructor(private user_service: UserService){}
  
    ngOnInit(){
   /* this.user_service
    .getUser()
    .subscribe( 
      data =>{
        this.listuser = data;
      });*/
    
     

   }

}