<<<<<<< HEAD
import { Component, OnInit } from '@angular/core';
=======
import { RegisterService } from './../services/register.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
>>>>>>> 78e6d76f3ded2a0287329aee6f0db53f4bdf8c10

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
<<<<<<< HEAD
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
=======
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
>>>>>>> 78e6d76f3ded2a0287329aee6f0db53f4bdf8c10
