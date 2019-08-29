import {Component, Input, OnInit} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { Play } from '../shared/model/play';
import { PlayService } from '../play.service';

@Component({
  selector: 'app-play-edit',
  templateUrl: './play-editor.component.html',
  styleUrls: ['./play-editor.component.less']
})
export class PlayEditorComponent implements OnInit {

  @Input() play: Play;
  editPlay: FormGroup;

  constructor(private formBuilder: FormBuilder, private playService: PlayService) {
    this.editPlay = this.formBuilder.group({
      title: ''
    });
  }

  ngOnInit() {
    this.editPlay.controls.title.setValue(this.play.title);
  }

  onSubmit(playData) {
    this.playService.updatePlay(playData).subscribe(edited => {
      this.play = edited;
    }, error => {
      console.log(error);
    });
  }

}
