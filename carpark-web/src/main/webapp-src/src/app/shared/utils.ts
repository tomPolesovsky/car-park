import {FormControl, FormGroup} from "@angular/forms";
import {HttpHeaders, HttpParams} from "@angular/common/http";

export const requestPath = '//localhost:8080/pa165/rest';

export function touchAllChildren(formGroup: FormGroup): void {
  const children = Object.values(formGroup.controls);
  children
    .filter(child => child instanceof FormGroup)
    .forEach(touchAllChildren);
  children
    .filter(child => child instanceof FormControl)
    .forEach(control => control.markAsTouched());
}

export const PAGE_SIZE = 10;

export function enumToArray(enumObject): string[] {
  const options = [];

  Object.values(enumObject).forEach(value => {
    options.push(value);
  });

  return options;
}

export const LOCAL_FORMAT = 'YYYY-MM-DD[T]HH:mm:ss';
