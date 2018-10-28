import { Router} from '@angular/router';
import { LoginService } from './../services/login.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Check_Pass } from './../models/response-models/check-pwd.model';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
 public checkPwd: Check_Pass[]=[];
  constructor(
    private loginService: LoginService,
    private formBuilder: FormBuilder,
    private router : Router
 
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username:[''],
      password: ['']
    });
   
  }

  login(){
    this.loginService.login(this.loginForm.value).subscribe(
      data =>{
        this.checkPwd = data;
        console.log("inside sub"+this.checkPwd);
        this.get();
      }
    );//end subscribe()
   }//end login()
  get(){
 
  if(this.checkPwd["doesPass"] == "TRUE"){
    this.router.navigate(['/NoteForm']);
  }

}

}//end class
