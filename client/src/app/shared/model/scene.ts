import {Colour} from './colour';

export class Scene {

  title?: string;
  readonly backgrounds: Background[] = [];
  backgroundColour?: Colour;
  readonly tracks: Track[] = [];

}

export enum DisplayMode {
  STRETCH,
  CENTER,
  FILL_WIDTH
}
export class Background {
  constructor(readonly href: URL, readonly width: number, readonly height: number, readonly mode: DisplayMode = DisplayMode.STRETCH) {
  }
}

export class Track {
  constructor(readonly title: string, readonly href: URL, readonly type: string) {
  }
}
