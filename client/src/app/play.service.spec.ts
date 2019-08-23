import { TestBed } from '@angular/core/testing';

import { PlayService } from './play.service';
import { HttpClientModule } from '@angular/common/http';

describe('PlayService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [ HttpClientModule ]
  }));

  it('should be created', () => {
    const service: PlayService = TestBed.get(PlayService);
    expect(service).toBeTruthy();
  });
});
