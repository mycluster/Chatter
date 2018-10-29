import { Message, User } from './';

export class ChatterMessage extends Message{
  constructor(from: User, content: string) {
    super(from, content);
  }
}