import { User } from './user';
import { Action } from './action';
import { Timestamp } from 'rxjs';
import { Time } from '@angular/common';

export class Message{
  constructor(
  public id: number,
  public message: any,
  public sender: User,
  public receiver: string,
  public sentAt: Time
  ){}
}
