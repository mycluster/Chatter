import { RegisterService } from './../services/register.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
 
  
  constructor(
    private registerService: RegisterService,
    private formBuilder: FormBuilder,
 ) {}

  ngOnInit() {
   
   this.registerForm = this.formBuilder.group({
     firstname: [''],
     lastname: [''],
     username: [''],
     email: [''],
     password: [''],
     confirm_password: ['']
    });
  }

  register(){
    
   
    if (this.registerForm.value["password"] == this.registerForm.value["confirm_password"]){
      console.log("inside if");
      this.registerService.register(this.registerForm.value).subscribe();
    }
    else{
      console.log("inside else");
    }//end else

  }//end register()
}//end class
