import { NoteFormFindModel } from './../models/response-models/note-form-find.model';
import { NoteFormService } from './../services/note-form.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';


@Component({
    selector: 'NoteForm',
    templateUrl: './note-form.component.html',
    styleUrls: ['./note-form.component.css']
})
export class NoteFormComponent implements OnInit {
    public getNote: NoteFormFindModel[]=[];

    noteForm: FormGroup;
    constructor(
        private formBuilder: FormBuilder,
        private noteServe:NoteFormService) { }

    ngOnInit() {
        this.noteForm = this.formBuilder.group({
            notename: [''],
            note: ['']
        });
    }

    onSubmit() {
        this.noteServe.sendNote(this.noteForm.value).subscribe();

    }
    find(){
        this.noteServe.findNote(this.noteForm.value["notename"]).subscribe(
            data =>{
                this.getNote = data;
                console.log("inside sub"+this.getNote);
              }

        );//end find()
    }//end find()


}
