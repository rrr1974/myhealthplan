import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  show = true; 
  get firstName(): string{
    if(this.dataService.firstName != ''){
      this.show=true;
    }
    return this.dataService.firstName;
  }

  get lastName(): string{
    if(this.dataService.lastName != ''){
      this.show=true;
    }
    return this.dataService.lastName;
  }

  set firstName(firstName: string){
    this.dataService.firstName=firstName;
  }

  set lastName(lastName: string){
    this.dataService.lastName=lastName;
  } 

  
  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

}
