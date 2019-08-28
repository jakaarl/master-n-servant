import {Scene} from './scene';

export class Play {
  constructor(title: string) {
  }
  readonly scenes: Scene[] = [];
  addScene(scene: Scene) {
    this.scenes.push(scene);
  }
}
