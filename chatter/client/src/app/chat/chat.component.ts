import { Component, OnInit, ViewChildren, ViewChild, AfterViewInit, QueryList, ElementRef } from '@angular/core';
import { MatDialog, MatDialogRef, MatList, MatListItem } from '@angular/material';
import { Action } from './shared/model/action';
import { Event } from './shared/model/event';
import { Message } from './shared/model/message';
import { User } from './shared/model/user';
import { SocketService } from './shared/services/socket.service';
import { DialogUserComponent } from './dialog-user/dialog-user/dialog-user.component';
import { DialogUserType } from './dialog-user/dialog-user/dialog-user-type';
import { MessageService } from './shared/services/message.service';


import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import { MessageInter } from './shared/model/message_interface';
import { UserInter } from './shared/model/user_interface';

const WSP = 'https://tools.ietf.org/html/rfc6455';



@Component({
  selector: 'tcc-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})

export class ChatterboxComponent implements OnInit, AfterViewInit {
  action = Action;
  username: string;
  user: User;
  messages: Message[] = [];
  newMessage = {
    message: ""
  }
  feedback: string ="";
  messageContent: string;
  ioConnection: any;
  dialogRef: MatDialogRef<DialogUserComponent> | null;
  defaultDialogUserParams: any = {
    disableClose: true,
    data: {
      title: 'Welcome to Chatterbox',
      dialogType: DialogUserType.NEW
    }
  };

  @ViewChild(MatList, { read: ElementRef }) matList: ElementRef;

  @ViewChildren(MatListItem, { read: ElementRef }) matListItems: QueryList<MatListItem>;


  constructor(private socketService: SocketService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.initModel();
    // Using timeout due to https://github.com/angular/angular/issues/14748
    setTimeout(() => {
      this.openUserPopup(this.defaultDialogUserParams);
    }, 0);
  }

  ngAfterViewInit(): void {
    // subscribing to any changes in the list of items / messages
    this.matListItems.changes.subscribe(elements => {
      this.scrollToBottom();
    });
  }


  private scrollToBottom(): void {
    try {
      this.matList.nativeElement.scrollTop = this.matList.nativeElement.scrollHeight;
    } catch (err) {
    }
  }

  private initModel(): void {
    this.MessageService.selectAllMessages.subscribe(
      data =>{
        this.messages = data;
      },
      error =>{ console.log("Messages could not be loaded.")});

    this.insertMessage();
    
  }

  insertMessage(){
    this.feedback = "";
    this.MessageService.insertMessage(this.newMessage.message)
    .subscribe(
      message => {
        this.feedback = message['message'];
        this.ngOnInit();
      },
      error => {
        console.log(error.message);
      }
    )
  };
  private initIoConnection(): void {
    this.socketService.initSocket();

    this.ioConnection = this.socketService.onMessage()
      .subscribe((message: Message) => {
        this.messages.push(message);
      });

    this.socketService.onEvent(Event.CONNECT)
      .subscribe(() => {
        console.log('User has been disconnected');
      });
    
  }
  // private getRandomId(): number {
  //     return Math.floor(Math.random() * (999999)) + 1;
  //   }

  public onClickUserInfo() {
    this.openUserPopup({
      data: {
        username: this.user.username,
        title: 'Edit details',
        dialogType: DialogUserType.EDIT
      }
    });
  }

  private openUserPopup(params): void {
    this.dialogRef = this.dialog.open(DialogUserComponent, params);
    this.dialogRef.afterClosed().subscribe(paramsDialog => {
      if (!paramsDialog) {
        return;
      }
    

      this.user.username = paramsDialog.username;
      if (paramsDialog.dialogType === DialogUserType.NEW) {
        this.initIoConnection();
        this.sendNotification(paramsDialog, Action.JOINED);
      }else if (paramsDialog.dialogType === DialogUserType.EDIT){
        this.sendNotification(paramsDialog, Action.RENAME);
      }
  

  });
}
  //Commented out in order to test base functionality first
  // public selectNMostRecentByConversation(params): string {
  // this.MessageService.selectNMostRecentByConversation()
  //   .subscribe(
  //     data => {
  //       this.user = {username: data.username};
  //       this.messages = data;
  //   }, error =>{ console.log("Messages cannot be loaded.")});
  // }

  public sendMessage(message: string): void {
    if (!message) {
      return;
    }

    this.socketService.send({
      from: this.user,
      content: message
    });
    this.messageContent = null;
  }

  public sendNotification(params: any, action: Action): void {
    let message: Message;

    if (action === Action.JOINED) {
      message = {
        from: this.username,
        content: {
          username: this.user.username,
          previousUsername: params.previousUsername
        }
      };
    }

    this.socketService.send(message);

  }
}
