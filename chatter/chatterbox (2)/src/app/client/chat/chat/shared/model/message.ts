//The message itself populates the from the 
//user's inputted name from the user interface.
//Content allows the user to input a message of 
//360 characters in a input that upon pressing 
//enter will generate in the chatterbox.
//The action the user performs allows them to 
//rename themselves, enter or leave the chat
//session. Future implementations will allow
//the user to change between conversations 
//with other users and edit the message itself.
import { User } from './user';
import { Action } from './action';

export interface Message{
  
  from?: User;
  content?: any;
  action?: Action;

}