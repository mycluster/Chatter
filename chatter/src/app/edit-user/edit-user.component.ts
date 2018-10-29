import { EditUser_Serv } from './../services/edit-user.service';

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';


@Component({
    selector: 'EditUser',
    templateUrl: './edit-user.component.html',
    styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponent implements OnInit {

  userForm: FormGroup;
  constructor(
      private formBuilder: FormBuilder,
      private editUsr_serv : EditUser_Serv) { }

    ngOnInit() {
        this.userForm = this.formBuilder.group({
            name: [''],
            major: [''],
            email: ['']
        });
    }

    submit(){
        this.editUsr_serv.sendEditUsr(this.userForm.value).subscribe();
    }

}
