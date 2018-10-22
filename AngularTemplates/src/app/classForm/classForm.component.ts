import { Component, OnInit, Host } from '@angular/core';

@Component({
  selector: 'basic-template-driven-form',
  templateUrl: './classForm.component.html'
})
export class ClassFormComponent implements OnInit {
  form: any;
  subjects: string[];
  submitted: boolean = false;

  constructor() { }

  ngOnInit() {
    this.subjects = ['English', 'Math',
                'Computer Science', 'Basket weaving'];
  }

  onSubmit(form: any)  {
    this.submitted = true;
    this.form = form;
  }
}
