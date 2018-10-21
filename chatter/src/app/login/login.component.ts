import { Component, OnInit} from '@angular/core';
//import {FormBuilder, FormGroup} from '@angular/forms';
import {UserService} from '../services/user.service';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({   
    selector:"app-login",
    templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit{
    loginForm : FormGroup;
    constructor(
        private userService:UserService,
        private formBuilder: FormBuilder){}
   

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            
            username: [''],
           
        });
    }
    formSubmit(){
      

      this.userService.login(this.loginForm.value).subscribe() ;
    
    }
}