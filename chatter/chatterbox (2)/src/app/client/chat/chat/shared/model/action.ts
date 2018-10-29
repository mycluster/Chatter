//The action the user performs allows them to 
//rename themselves, enter or leave the chat
//session. Future implementations will allow
//the user to change between conversations 
//with other users and edit the message itself.
export enum Action {
    EDIT,
    SWITCHCONVO,
    JOINED,
    LEFT,
    RENAME
  }