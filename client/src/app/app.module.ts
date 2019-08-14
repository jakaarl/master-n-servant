import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ClickMeModule } from './click-me/click-me.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    ClickMeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
