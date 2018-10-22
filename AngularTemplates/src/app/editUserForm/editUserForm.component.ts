import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user';
import { FormGroup } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { ValidationService } from '../shared/validation.service';

@Component({
    selector: 'editUserForm',
    templateUrl: 'editUserForm.component.html'
})
export class EditUserFormComponent implements OnInit {
    model: User;
    userForm: FormGroup;
    formModelProps = [];
    submitted: boolean;

    constructor() { }

    ngOnInit() {
        this.model = new User(18, 'Dr IQ', 'Computer Science',  'iq@superhero.com');

        const formModel = {};
        let validators = [ Validators.required ];
        for (const prop of Object.keys(this.model)) {
            if (prop.indexOf('email') !== -1) validators.push(ValidationService.emailValidator);
            formModel[prop] = new FormControl(this.model[prop], validators);
            this.formModelProps.push(prop);
        }
        this.userForm = new FormGroup(formModel);
    }

    submit() {
        this.submitted = true;
    }

}
