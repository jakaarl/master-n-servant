import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';

import { PlayEditorComponent } from './play-editor.component';
import { HttpClientModule } from '@angular/common/http';

describe('PlayEditorComponent', () => {
  let component: PlayEditorComponent;
  let fixture: ComponentFixture<PlayEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayEditorComponent ],
      imports: [
        HttpClientModule, // required by PlayService
        ReactiveFormsModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
