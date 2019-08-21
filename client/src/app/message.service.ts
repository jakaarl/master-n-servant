import { Injectable } from '@angular/core';
import * as socketClient from 'socket.io-client';
import { Observable } from 'rxjs';
import { environment} from '../environments/environment';

const serverUri = environment.messageServerUri;

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

  public initSocket(): void {
    this.initWithSocket(socketClient(serverUri));
  }

  public initWithSocket(socket: SocketIOClient.Socket) {
    this.socket = socket;
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
