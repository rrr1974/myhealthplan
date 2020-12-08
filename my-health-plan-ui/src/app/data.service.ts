import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  firstName: string='';
  lastName:string ='';
  show=false;

  constructor() { }
}
