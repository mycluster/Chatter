import { Component, OnInit, ViewChildren, ViewChild, AfterViewInit, QueryList, ElementRef } from '@angular/core';
import { MatDialog, MatDialogRef, MatList, MatListItem } from '@angular/material';
import { Action } from './shared/model/action';
import { Event } from './shared/model/event';
import { Message } from './shared/model/message';
import { User } from './shared/model/user';
import { SocketService } from './shared/services/socket.service';
import { DialogUserComponent } from 'src/app/client/chat/chat/dialog-user/dialog-user.component';
import { DialogUserType } from 'src/app/client/chat/chat/dialog-user/DialogUserType';

const WSP = 'https://tools.ietf.org/html/rfc6455';

//The chat component implements the SocketService to provide bidirectional,
//real-time communication between users. It listens for events over the TCP
//and subscribes to messages transmitted and events performed such as when the
//user enters,leaves, or renames themselves. 
@Component({
  selector: 'tcc-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})

//ChatComponent imports the action and event enums as well as the user and message
//interfaces. The user's name is assigned by the user themselves and the message
//takes a string and transmits it over any available socket connection.
//The dialogRef outputs events such as when the user enters or leaves the 
//chatterbox. Upon initially entering the window, they are greeted as having 
//joined the chat session as their inputted username. 
export class ChatComponent implements OnInit {
  action = Action;
  user: User;
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

  //ngOnInit initializes the chatterbox and keeps the input username 
  //popUp open until the user inputs their username.
  ngOnInit(): void {
    this.initModel();
    setTimeout(() => {
      this.openUserPopup(this.defaultDialogUserParams);
    }, 0);
  }

  //ngAfterViewInit() listens for messages that are populated
  //in the chatterbox and keeps the browser scrolled to the 
  //bottom of the chat so they may see the most recent messages.
  ngAfterViewInit(): void {
    this.matListItems.changes.subscribe(elements => {
      this.scrollToBottom();
    });
  }

  //The initModel abstracts the ngOnInit to provide the user with a 
  //randomly generated id that is assigned to them. This id remains
  //with the user until they exit the chat session by closing the tab
  //or refreshing the page.
  private initModel(): void {
    const randomId = this.getRandomId();
    this.user = {
      id: randomId
    }
  }

  //When the chat session becomes populated with enough messages to fill the screen,
  //the scrollToBottom function keeps the most recent messages displayed as they 
  //are transmitted over the socket.
  private scrollToBottom(): void {
    try {
      this.matList.nativeElement.scrollTop = this.matList.nativeElement.scrollHeight;
    } catch (err) {
    }
  }

  //initIoConnection() establishes communication from the client to the server 
  //over the socket and enables it to receive real-time communication in the 
  //form of messages. It also subscribes to connect and disconnect events 
  //for when the user opens or closes the chatterbox.
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
  //getRandomId generates a random number and assigns it to the 
  //user until they close or refresh the browser.
  private getRandomId(): number {
    return Math.floor(Math.random()* (999999)) + 1;
  }

  //When the user first opens chatterbox, they are prompted with 
  //a dialog box to enter their username. Communication is then 
  //established between the client and server and the event 
  //triggers display in the chatterbox that the user's status has 
  //changed.
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
  //The sendMessage transmits the message over the TCP layer upon the 
  //user pressing enter along with their username and the content of 
  //the message.
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

  //When the user joins, leaves, or renames themselves sendNotification() 
  //notifies other users in the chatterbox.
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