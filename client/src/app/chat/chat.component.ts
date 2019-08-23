import {Component, OnInit} from '@angular/core';
import {Event, MessageService} from '../message.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.less']
})
export class ChatComponent implements OnInit {

  private messageService: MessageService;

  constructor() {
    this.messageService = MessageService.withPath('/ws');
  }

  ngOnInit() {
    console.log('Initializing chat component');
    this.messageService.onEvent(Event.CONNECT).subscribe(() => {
      console.log('Message service connected.');
    });
    this.messageService.onMessage().subscribe((message) => {
      console.log(message);
    });
    this.messageService.send({ payload: 'Hello world!'});
  }

}
