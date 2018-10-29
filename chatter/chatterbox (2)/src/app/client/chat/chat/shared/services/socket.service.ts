import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../model/message'
import { Event } from '../model/event';

import * as socketIo from 'socket.io-client';

const SERVER_URL = 'http://localhost:4200';

//The injectable directive for the socket allows for new messages and events to 
//be transmitted over a single TCP connection. This allows for bidirectional 
//communication without waiting for a request from the client. It also allows
//for the port to stay open while messages are being sent back and forth, 
//while providing lower overhead than the http protocol. Socket.io is used 
//to provide event-based communication while also auto-detecting when the 
//connection is established or lost. It has a Node.js server and though we use
//TypeScript, its library is based on JavaScript to provide client support. 
@Injectable()
export class SocketService {
  private socket;

  //The socket is established upon the client connecting to the server.
  //The url provided will direct the client to the appropriate port.
  //In our case, we used port 4200.
  public initSocket(): void {
    this.socket = socketIo(SERVER_URL);
  }

  //When the user presses enter, the content of the message is communicated
  //over the TCP layer and displayed in the chatterbox.
  public send(message: Message): void {
    this.socket.emit('message', message);
  }

  //When the message is entered by one user, the client on the other browser
  //listens for the message, parses, and displays it in their chatter window.
  public onMessage(): Observable<Message> {
    return new Observable<Message>(observer => {
      this.socket.on('message', (data: Message) => observer.next(data));
    });
  }

  //When the user enters, leaves, or renames themselves, the Observable
  //registers the appropriate event and displays to screen the appropriate
  //event.
  public onEvent(event: Event): Observable<any> {
    return new Observable<Event>(observer => {
      this.socket.on(event, () => observer.next());
    });

  }

}
