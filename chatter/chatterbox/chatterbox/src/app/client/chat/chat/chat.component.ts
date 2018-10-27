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
  username: string;
  username2: string;
  user: User;
  n: 30;
  messages: Message[] = [];
  newMessage = {
    id: "",
    message: "",
    sender: "",
    receiver: "",
    sentAt: "",
    edited: "",
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
  
  constructor(private socketService: SocketService,
    public dialog: MatDialog) { }
  ngOnInit() {
  }


}