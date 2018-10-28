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
  //sentAt= this.sentAt;
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
    setTimeout(() => {
      this.openUserPopup(this.defaultDialogUserParams);
    }, 0);
  }

  ngAfterViewInit(): void {
    this.matListItems.changes.subscribe(elements => {
      this.scrollToBottom();
    });
  }

  private initModel(): void {
    const randomId = this.getRandomId();
    this.user = {
      id: randomId
    }
  }


  private scrollToBottom(): void {
    try {
      this.matList.nativeElement.scrollTop = this.matList.nativeElement.scrollHeight;
    } catch (err) {
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
        console.log('User has entered chat');
      });

    this.socketService.onEvent(Event.DISCONNECT)
      .subscribe(() => {
       console.log("User has left chat");
      });
  }

  public onClickUserInfo() {
    this.openUserPopup({
      data: {
        username: this.user.name,
        title: 'Edit details',
        dialogType: DialogUserType.EDIT
      }
    });
  }

  private getRandomId(): number {
    return Math.floor(Math.random()* (999999)) + 1;
  }


  private openUserPopup(params): void {
    this.dialogRef = this.dialog.open(DialogUserComponent, params);
    this.dialogRef.afterClosed().subscribe(paramsDialog => {
      if (!paramsDialog) {
        return;
      }

      this.user.name = paramsDialog.username;
      if (paramsDialog.dialogType === DialogUserType.NEW) {
        this.initIoConnection();
        this.sendNotification(paramsDialog, Action.JOINED);
      }else if (paramsDialog.dialogType === DialogUserType.EDIT){
        this.sendNotification(paramsDialog, Action.EDIT);
      }
  

  });
}

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
        from: this.user,
        action: action
      }
    
    }else if (action === Action.RENAME){
      message = {
        action: action,
        content: {
          username: this.user.name,
          previousUsername: params.previousUsername
        }
      };
    }

    this.socketService.send(message);

  }
}