import { TestBed } from '@angular/core/testing';

import { MessageService, Message } from './message.service';
import SocketMock from 'socket.io-mock';

describe('MessageService', () => {
  let service: MessageService;
  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.get(MessageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('socket', () => {
    let socket: SocketMock;
    beforeEach(() => {
      socket = new SocketMock();
      service.initWithSocket(socket);
    });

    it('should relay messages', () => {
      const payload = 'Hello there!';
      service.onMessage().subscribe((message: Message) => {
        expect(message.payload).toEqual(payload);
      });
      socket.emit('message', { payload });
    });
  });
});
