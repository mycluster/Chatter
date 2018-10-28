import { User_Classes_Service } from './../services/user-classes.service';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'UserClasses',
  templateUrl: './user-classes.component.html',
  styleUrls: ['./user-classes.component.scss']
})
export class UserClassesComponent  {
 // public classes: User_Classes[]=[];->will need
 classes:string;
 
 //public usr_classes_service: User_Classes_Service;
 //selected: string[];
 
  /*
  *dont really know what these are for from luster so these are omitted for now
  user: any;
  message: string;
  noteName: string;
  form: any;
  submitted: boolean = false;*/
  constructor(private usr_classes_service: User_Classes_Service ) { }


    selected =[{ name: "Class01", value: "Class1" },
    { name: "English101", value: "English101"},
    { name: "Finance101", value: "Finance101" }
  ]
    // ['Class1', 'English101', 'Finance101']


  onSubmit(){
  
    console.log(this.classes);
    this.usr_classes_service.sendSubject(this.classes).subscribe();
  }

}
