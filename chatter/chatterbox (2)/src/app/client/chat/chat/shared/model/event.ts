//The exported enums communicate with the 
//socket to signal that a connection has 
//either been established or lost.
export enum Event {
    CONNECT = 'connect',
    DISCONNECT = 'disconnect'
  }