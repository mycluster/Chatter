import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl, Validators } from '@angular/forms';

//The dialog-user component enables the user to 
//indicate to the socket which username they would 
//like to use. The string that they enter is then 
//injected into the html template and displays on 
//screen and notifies other members of the chat
//session that a new user has entered or renamed
//themselves. Future implementations will keep this
//communication private to enable users to remain
//anonymous should they choose.
@Component({
  selector: 'tcc-dialog-user',
  templateUrl: './dialog-user.component.html',
  styleUrls: ['./dialog-user.component.css']
})

//When the socket connection is established, the
//DialogUserComponent takes in user input as a string
// and displays it to the screen as that user entering 
//the chat session. Should the user change their name,
//the username entered is reassigned and displays to
//screen that the original username's 'is now' the 
//respecified username.
export class DialogUserComponent implements OnInit {
  usernameFormControl = new FormControl('', [Validators.required]);
  previousUsername: string;

  constructor(public dialogRef: MatDialogRef<DialogUserComponent>,
    @Inject(MAT_DIALOG_DATA) public params: any) {
      this.previousUsername = params.username ? params.username : undefined;

     }

  ngOnInit() {
  }
  //When the username clicks the Chatter icon at the 
  //top right of the screen, they are able to edit their 
  //username. This value is then reassigned and chatterbox
  //notifies the other users in the chat session that
  //the user has switched usernames.
  public onSave(): void {
    this.dialogRef.close({
      username: this.params.username,
      dialogType: this.params.dialogType,
      previousUsername: this.previousUsername
    });
  }

}
