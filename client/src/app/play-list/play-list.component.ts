import { Component, OnInit } from '@angular/core';
import { Play } from '../shared/model/play';
import { PlayService } from '../play.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-play-list',
  templateUrl: './play-list.component.html',
  styleUrls: ['./play-list.component.less']
})
export class PlayListComponent implements OnInit {

  plays: Play[] = [];

  constructor(private playService: PlayService) { }

  ngOnInit() {
    this.fetchPlays().subscribe(plays => {
      this.plays = plays;
    }); // TODO: error handling
  }

  private fetchPlays(): Observable<Play[]> {
    return this.playService.listPlays();
  }

}
