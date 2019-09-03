import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Play } from '../shared/model/play';
import { PlayService } from '../play.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-play-list',
  templateUrl: './play-list.component.html',
  styleUrls: ['./play-list.component.less']
})
export class PlayListComponent implements OnInit {

  plays: Play[] = [];

  constructor(private playService: PlayService, private router: Router) { }

  ngOnInit() {
    this.fetchPlays().subscribe(plays => {
      this.plays = plays;
    }); // TODO: error handling
  }

  createNewPlay() {
    console.log('Creating new play.');
    this.playService.createPlay(Play.DEFAULT_TITLE).subscribe(play => {
      console.log(`Created play: ${play.id}`);
      this.router.navigate([`/plays/${play.id}`]);
    });
  }

  private fetchPlays(): Observable<Play[]> {
    return this.playService.listPlays();
  }

}
