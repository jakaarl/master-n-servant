import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, RouterModule } from '@angular/router';

import { PlayEditorComponent } from './play-editor.component';
import { Play } from '../shared/model/play';
import { PlayService } from '../play.service';
import { of } from 'rxjs';

describe('PlayEditorComponent', () => {
  let component: PlayEditorComponent;
  let fixture: ComponentFixture<PlayEditorComponent>;

  const playService = jasmine.createSpyObj('PlayService', ['fetchPlay', 'updatePlay']);
  const activatedRoute = {
    paramMap: of(new Map<string, string>([['id', 'abcd-efgh']]))
  };
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayEditorComponent ],
      imports: [
        HttpClientTestingModule,
        ReactiveFormsModule,
        RouterModule
      ],
      providers: [
        { provide: PlayService, useValue: playService },
        { provide: ActivatedRoute, useValue: activatedRoute }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayEditorComponent);
    component = fixture.componentInstance;
    playService.fetchPlay.and.returnValue(of(new Play(null, 'Test play')));
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display title', () => {
    const componentElement = fixture.debugElement.nativeElement;
    const inputElement = componentElement.querySelector('input#title');
    expect(inputElement).not.toBeNull();
    expect(inputElement.value).toContain('Test play');
  });

  describe('submit', () => {
    it('should update title', () => {
      const updatedPlay = new Play(null, 'Updated play');
      playService.updatePlay.and.returnValue(of(updatedPlay));
      component.onSubmit(updatedPlay);
      const componentElement = fixture.debugElement.nativeElement;
      const inputElement = componentElement.querySelector('input#title');
      expect(inputElement).not.toBeNull();
      expect(inputElement.value).toContain('Updated play');
    });
  });

});
