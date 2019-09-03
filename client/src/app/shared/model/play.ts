import {Scene} from './scene';

export class Play {

  static readonly DEFAULT_TITLE = 'Untitled';
  readonly scenes: Scene[] = [];

  constructor(readonly id: string, readonly title: string = Play.DEFAULT_TITLE) {
  }

  addScene(scene: Scene) {
    this.scenes.push(scene);
  }
}
