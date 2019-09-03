import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import {Router, RouterModule} from '@angular/router';
import { of } from 'rxjs';

import { PlayListComponent } from './play-list.component';
import { PlayService } from '../play.service';

describe('PlayListComponent', () => {
  let component: PlayListComponent;
  let fixture: ComponentFixture<PlayListComponent>;

  const playService = jasmine.createSpyObj('PlayService', ['listPlays']);
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule, RouterModule ],
      declarations: [ PlayListComponent ],
      providers: [
        { provide: PlayService, useValue: playService },
        { provide: Router }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayListComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('when there are no plays', () => {
    beforeEach(() => {
      playService.listPlays.and.returnValue(of([]));
      fixture.detectChanges();
    });
    it('should not display list', () => {
      const componentElement = fixture.debugElement.nativeElement;
      const listElement = componentElement.querySelector('ul.playList');
      expect(listElement).toBeNull();
    });

    it('should display a notice', () => {
      const componentElement = fixture.debugElement.nativeElement;
      const noticeElement = componentElement.querySelector('p.notice');
      expect(noticeElement).not.toBeNull();
      expect(noticeElement.textContent).toContain('no plays');
    });
  });

});
