import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'note-form',
    templateUrl: './noteForm.component.html'
})
export class noteFormComponent implements OnInit {
    user: any;
    message: string;
    noteName: string;

    constructor() { }

    ngOnInit() {

        this.user = {
            note: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum',
            noteName: 'Latin Notes'
        };
    }

    onSubmit() {
        this.message = 'Note Name: '+ this.user.noteName+": " + this.user.note;

    }

}
