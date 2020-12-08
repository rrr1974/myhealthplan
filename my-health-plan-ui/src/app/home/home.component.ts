import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '../_models';
import { EOB } from '../_models';
import { UserService } from '../_services';
import { HttpRequest, HttpResponse, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { of, throwError } from 'rxjs';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'home.component',
  templateUrl: 'home.component.html'
})
export class HomeComponent implements OnInit {

  /* sites: Sites[] = [
    {value: 'sites-0', viewValue: 'mymedicare.gov'},
    {value: 'sites-1', viewValue: 'aetna.com'},
    {value: 'sites-2', viewValue: 'metlife.com'},
    {value: 'sites-3', viewValue: 'eyemed.com'},
    {value: 'sites-4', viewValue: 'express-scripts.com'},
    {value: 'sites-5', viewValue: 'wageworks.com'}
  ]; */

  sites = new FormControl();
  siteList: string[] = ['mymedicare.gov', 'aetna.com', 'metlife.com', 'eyemed.com', 'express-scripts.com', 'wageworks.com'];
     users: User[] = [];
     eobsArray : any;
  

    constructor(private userService: UserService) {}

    ngOnInit() {
        this.userService.retrieveFHIRData();
      
      
      
      this.userService.getAll().pipe().subscribe(eobsArray => { 
            this.eobsArray = eobsArray; 
        });
           
    }

    
}

interface Sites {
  value: string;
  viewValue: string;
}