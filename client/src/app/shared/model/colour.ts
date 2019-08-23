export class Colour {
  static BLACK = new Colour(0, 0, 0);
  static WHITE = new Colour(255, 255, 255);

  constructor(readonly r: number, readonly g: number, readonly b: number) {
    for (const value of [r, g, b]) {
      if (value < 0 || value > 255) {
        throw Error(`Invalid colour value: ${value}`);
      }
    }
  }

  rgb(): string {
    return `rgb(${this.r},${this.g},${this.b})`;
  }
}
