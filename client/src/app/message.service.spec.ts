import { Event, Message, MessageService } from './message.service';
import SocketMock from 'socket.io-mock';

describe('MessageService', () => {
  describe('socket handlers', () => {
    let service: MessageService;
    let socket;

    beforeEach(() => {
      socket = new SocketMock();
      service = new MessageService(socket);
    });

    it('should observe messages', (done) => {
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
