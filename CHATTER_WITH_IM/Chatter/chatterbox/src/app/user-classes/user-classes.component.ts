import { UserClassesService } from './../services/user-classes.service';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'UserClasses',
  templateUrl: './user-classes.component.html',
  styleUrls: ['./user-classes.component.css']
})
export class UserClassesComponent  {
 classes:string;
  
  constructor(private usr_classes_service: UserClassesService ) { }
    selected =[{ name: "Class01", value: "Class1" },
    { name: "English101", value: "English101"},
    { name: "Finance101", value: "Finance101" }
  ]

  onSubmit(){
    console.log(this.classes);
    this.usr_classes_service.sendSubject(this.classes).subscribe();
  }

}
