import {UserService} from '../service/user.service';
import { Component, OnInit} from '@angular/core';
import { User } from '../model/user';


@Component({
    selector:"iuser-list",
    templateUrl:"list.component.html",
    providers:[UserService]
})
export class UserListComponent implements OnInit{

 public users:User[] =[];
 
   
    constructor(private user_service: UserService){}
  
    ngOnInit(){
    let fill = "fillerVal";
    this.user_service
    .getUser(fill)
    .subscribe( 
      data =>{
        this.users = data;
    //     let obj =listuser;
    //   var arr = Object.keys(obj).map(key => ({type: key, value: obj[key]}));
  //  var keys = Object.keys(this.users).sort();
    console.log(this.users["username"]);
     //console.log(this.users);
     // const myArrStr = JSON.stringify(this.users);
    // console.log(typeof(arr));
    
      
      });
     

    }
     
}