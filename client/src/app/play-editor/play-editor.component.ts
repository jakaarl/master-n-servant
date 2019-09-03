import {Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { Play } from '../shared/model/play';
import { PlayService } from '../play.service';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-play-edit',
  templateUrl: './play-editor.component.html',
  styleUrls: ['./play-editor.component.less']
})
export class PlayEditorComponent implements OnInit {

  static readonly ID_PARAM = 'id';
  editPlay: FormGroup;

  constructor(private formBuilder: FormBuilder, private playService: PlayService, private activatedRoute: ActivatedRoute) {
    this.editPlay = this.formBuilder.group({
      id: '',
      title: ''
    });
  }

  ngOnInit() {
    this.activatedRoute.paramMap.pipe(
      switchMap(params => {
        const id = params.get(PlayEditorComponent.ID_PARAM);
        return this.playService.fetchPlay(id);
      })
    ).subscribe(play => {
      this.updateForm(play);
    });
  }

  onSubmit(playData) {
    this.playService.updatePlay(playData).subscribe(edited => {
      this.updateForm(edited);
    }, error => {
      console.log(error);
    });
  }

  private updateForm(updated: Play) {
    this.editPlay.controls.id.setValue(updated.id);
    this.editPlay.controls.title.setValue(updated.title);
  }

}
