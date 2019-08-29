import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { PlayEditorComponent } from './play-editor/play-editor.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PlayListComponent } from './play-list/play-list.component';

const routes: Routes = [
  { path: 'plays', component: PlayListComponent },
  { path: 'plays/:id', component: PlayEditorComponent },
  { path: '', redirectTo: '/plays', pathMatch: 'full'}
];
@NgModule({
  declarations: [
    AppComponent,
    PlayEditorComponent,
    PlayListComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(
      routes,
      { enableTracing: true }
    )
  ],
  providers: [ ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
