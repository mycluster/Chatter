import { Component, OnInit, ViewChildren, ViewChild, AfterViewInit, QueryList, ElementRef } from '@angular/core';
import { MatDialog, MatDialogRef, MatList, MatListItem } from '@angular/material';
import { Action } from './shared/model/action';
import { Event } from './shared/model/event';
import { Message } from './shared/model/message';
import { User } from './shared/model/user';
import { SocketService } from './shared/services/socket.service';
import { DialogUserComponent } from 'src/app/client/chat/chat/dialog-user/dialog-user.component';
import { DialogUserType } from 'src/app/client/chat/chat/dialog-user/DialogUserType';
import { MessageService } from 'src/app/client/chat/chat/shared/services/messageservice.service';
import { stringify } from '@angular/core/src/render3/util';

const WSP = 'https://tools.ietf.org/html/rfc6455';

@Component({
  selector: 'tcc-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  action = Action;
  user: User;
  // id = this.user.id;
  // username = this.user.username;
  // fName = this.user.fName;
  // lName = this.user.lName;
  messages: Message[] = [];
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
    this.user = {
      id: this.id,
      username: this.username,
      fName: this.fName,
      lName: this.lName
    }
     
  }

  private initIoConnection(): void {
    this.socketService.initSocket();

    this.ioConnection = this.socketService.onMessage()
      .subscribe((message: Message) => {
        this.messages.push(message);
      });

    this.socketService.onEvent(Event.CONNECT)
      .subscribe(() => {
        console.log('User has been Connected');
      });

    this.socketService.onEvent(Event.DISCONNECT)
      .subscribe(() => {
       console.log("User has disconnected");
      });
  }

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
    

      // this.user.username = paramsDialog.username;
      // if (paramsDialog.dialogType === DialogUserType.NEW) {
      //   this.initIoConnection();
      //   this.sendNotification(paramsDialog, Action.JOINED);
      // }else if (paramsDialog.dialogType === DialogUserType.EDIT){
      //   this.sendNotification(paramsDialog, Action.EDIT);
      // }
  

  });
}

  public sendMessage(message: Message): void {
    if (!message) {
      return;
    }

    this.socketService.send({
      id: message.id,
      message: message.message,
      sender: message.sender,
      receiver: message.receiver,
      sentAt: message.sentAt,
      edited: message.edited 
      
    });
    this.messageContent = null;
  }


  // public sendNotification(params: any, action: Action, receiver: User): void {

  //   if (action === Action.NEWMESSAGE) {
  //     action = {
  //       action: action,
  //     }

  //   }

  //   this.socketService.send(action);

  // }
}