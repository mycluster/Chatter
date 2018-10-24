import { User } from './user';
import { Action } from './action';
import { Timestamp } from 'rxjs';

export class Message{
  constructor(
  public id: number,
  public message: string,
  public sender: string,
  public receiver: string,

  ){}
}
