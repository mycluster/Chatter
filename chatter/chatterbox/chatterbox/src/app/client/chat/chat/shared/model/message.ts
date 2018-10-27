import { User } from './user';
import { Date } from 'rxjs';
//import { Date } from '@angular/common';
import { Action } from './action';

export interface Message{
  
  id: number;
  message: any;
  sender: User;
  receiver: User,
  sentAt: Date,
  edited: boolean,
  action?: Action,

}