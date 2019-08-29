import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { PlayService } from '../play.service';

@Component({
  selector: 'app-play-edit',
  templateUrl: './play-editor.component.html',
  styleUrls: ['./play-editor.component.less']
})
export class PlayEditorComponent implements OnInit {

  editPlay: FormGroup;

  constructor(private formBuilder: FormBuilder, private playService: PlayService, private router: Router) {
    this.editPlay = this.formBuilder.group({
      name: ''
    });
  }

  ngOnInit() {
  }

  onSubmit(playData) {
    this.playService.createPlay(playData.name).subscribe(_ => {
      this.router.navigate(['/plays']);
    }, error => {
      console.log(error);
    });
  }

}
