import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChatterModule } from '../chatter/chatter.module';
import { ChatComponent } from './chat/chat.component';

@NgModule({
  imports: [
    CommonModule,
    ChatterModule
  ],
  declarations: [ChatComponent]
})
export class ChatModule { }
