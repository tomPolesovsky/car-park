import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'separateThousands'
})
export class SeparateThousandsPipe implements PipeTransform {
  transform(value: string | number, thousandsDelimiter: string = ' '): string {
    const numericValue = Number(value);
    const [wholePart, decimalPart] = String(numericValue).split('.');
    const delimited = wholePart
      .split('')
      .reduce((acc, char, index, array) => `${acc}${(array.length - index) % 3 === 0 ? ' ' : ''}${char}`, '')
      .trim();
    return decimalPart != null ? `${delimited}.${decimalPart}` : delimited;
  }
}
