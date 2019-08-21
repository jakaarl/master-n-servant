import { TestBed } from '@angular/core/testing';

import { Event, Message, MessageService } from './message.service';
import SocketMock from 'socket.io-mock';
import createSpy = jasmine.createSpy;

describe('MessageService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  describe('socket handlers', () => {
    let service: MessageService;
    let socket;

    beforeEach(() => {
      service = TestBed.get(MessageService);
      socket = new SocketMock();
      service.initWithSocket(socket);
    });

    it('should observer messages', (done) => {
      const sent: Message = { payload: 'Hello there!' };
      service.onMessage().subscribe((message) => {
        expect(message).toEqual(sent);
        done();
      });
      socket.socketClient.emit('message', sent);
    });

    it('should observe connection events', (done) => {
      service.onEvent(Event.CONNECT).subscribe(() => {
        const connected = true;
        expect(connected).toBeTruthy(); // suppress no expectation warning
        done();
      });
      socket.socketClient.emit(Event.CONNECT, null);
    });

    it('should observe disconnect events', (done) => {
      service.onEvent(Event.DISCONNECT).subscribe(() => {
        const disconnected = true;
        expect(disconnected).toBeTruthy(); // suppress no expectation warning
        done();
      });
      socket.socketClient.emit(Event.DISCONNECT, null);
    });
  });
});
