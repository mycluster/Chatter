import { createServer, Server } from 'http';
import * as express from 'express';
import * as socketIo from 'socket.io';
import * as io from 'socket.io-client';
import { Chatterbox } from './chatterbox/chatterbox.component';

import { Message } from '@angular/compiler/src/i18n/i18n_ast';

export class ChatterboxServer {
  public static readonly PORT: number = 8080; //we may want to change this
  private app: express.Application;
  private server: Server;
  private io: SocketIO.Server;
  private port: string | number;

  constructor() {
    this.createApp();
    this.config();
    this.createServer();
    this.sockets();
    this.listen();
   
  }

  private createApp(): void {
    this.app = express();
  }

  private createServer() {
    this.server = createServer(this.app);
  }

  private config(): void {
    this.port = process.env.PORT || ChatterboxServer.PORT;
  }

  private sockets(): void {
    this.io = socketIo(this.server);
  }

  private listen(): void {
    this.server.listen(this.port, () => {
      console.log('Chatterbox is currently running on port %s', this.port);
    });

    this.io.on('connect', (socket: any) => {
      console.log('Client connected on port %s.', this.port);
      socket.on('message', (m: Message) => {
        console.log('[server](message): %s', JSON.stringify(m));
        this.io.emit('message', m);
      });
   

      socket.on('disconnect', () => {
      console.log('Client has been disconnected');
    });
  });
}

public getApp(): express.Application {
  return this.app;
}

}
