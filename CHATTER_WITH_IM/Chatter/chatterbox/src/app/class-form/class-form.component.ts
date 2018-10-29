import { ClassFormService } from '../services/class-form.service';
import { Component, OnInit, Host } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'basic-template-driven-form',
  templateUrl: './class-form.component.html',
  styleUrls: ['./class-form.component.css']
})
export class ClassFormComponent implements OnInit {
  classForm: FormGroup;
  subject:string;

  constructor(
    private class_form_serv: ClassFormService,
    private formBuilder: FormBuilder) { }

    ngOnInit(){
   this.classForm = this.formBuilder.group({
        username: [''],
        classname: [''],
      
      });
    }

  selected =[{ name: "Class01", value: "Class1" },
  { name: "English101", value: "English101"},
  { name: "Finance101", value: "Finance101" }
]

  onSubmit()  {
    console.log(this.subject);
    console.log(this.classForm.value);
    this.class_form_serv.sendSubject(this.subject,this.classForm.value).subscribe();
  }
}
