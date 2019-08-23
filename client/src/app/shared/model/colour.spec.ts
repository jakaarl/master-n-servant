import {Colour} from './colour';

describe('Colour', () => {
  describe('constructor', () => {
    it('throws on negative colour value', () => {
      expect(() => {
        const colour = new Colour(-1, 0, 0);
      }).toThrowError(/Invalid colour value/);
    });

    it('accepts valid colour values', () => {
      const colour = new Colour(1, 2, 3);
      expect(colour).toBeDefined();
    });
  });
});
