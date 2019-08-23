import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ChatComponent } from './chat/chat.component';
import { PlayEditorComponent } from './play-editor/play-editor.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PlayListComponent } from './play-list/play-list.component';

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    PlayEditorComponent,
    PlayListComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [ ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
