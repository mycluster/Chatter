import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'userclasses',
    templateUrl: './user-classes.component.html'
})
export class UserClassesComponent implements OnInit {
    user: any;
    message: string;
    noteName: string;
    classes: string[];

    constructor() { }

    ngOnInit() {
      this.classes = ['Class1', 'English101', 'Finance101'


    }


}
