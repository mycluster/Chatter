import { User } from './user';
import { Timestamp } from 'rxjs';
import { Time } from '@angular/common';

export class Message{
  constructor(
  public id: number,
  public message: any,
  public sender: User,
  public receiver: User,
  public sentAt: Date,
  public edited: boolean
  ){}
}