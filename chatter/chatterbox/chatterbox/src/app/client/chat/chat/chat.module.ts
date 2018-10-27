import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChatComponent } from './chat.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DialogUserComponent } from 'src/app/client/chat/chat/dialog-user/dialog-user.component';
import { MaterialModule } from './../../shared/material/material.module';
import { SocketService } from './shared/services/socket.service';
import { Component, OnInit, ViewChildren, ViewChild, AfterViewInit, QueryList, ElementRef } from '@angular/core';
import { MatDialog, MatDialogRef, MatList, MatListItem } from '@angular/material';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,

  ],
  declarations: [ChatComponent, DialogUserComponent],
  providers: [SocketService],
  //schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents: [DialogUserComponent]
})
export class ChatModule { }
