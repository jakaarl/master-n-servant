import { Injectable } from '@angular/core';
import * as socketClient from 'socket.io-client';
import { Observable } from 'rxjs';
import { environment} from '../environments/environment';

const wsServerUri = `${environment.backendServer}/ws`;

export enum Action {
  JOIN,
  LEAVE
}

export enum Event {
  CONNECT = 'connect',
  DISCONNECT = 'disconnect'
}

export interface Message {
  payload: any;
}

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private socket: SocketIOClient.Socket;

  constructor(socket: SocketIOClient.Socket) {
    this.socket = socket;
  }

  static withPath(path: string): MessageService {
    return new MessageService(socketClient(wsServerUri, { path, transports: ['websocket'] }));
  }

  public send(message: Message): void {
    this.socket.emit('message', message);
  }

  public onMessage(): Observable<Message> {
    return new Observable<Message>(observer => {
      this.socket.on('message', (data: Message) => observer.next(data));
    });
  }

  public onEvent(event: Event): Observable<Event> {
    return new Observable<Event>(observer => {
      this.socket.on(event, () => observer.next());
    });
  }

}
