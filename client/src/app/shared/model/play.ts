import {Scene} from './scene';

export class Play {
  readonly scenes: Scene[] = [];
  constructor(readonly id: string, readonly title: string) {
  }

  addScene(scene: Scene) {
    this.scenes.push(scene);
  }
}
