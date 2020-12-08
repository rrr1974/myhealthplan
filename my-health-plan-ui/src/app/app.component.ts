import { Component } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my-health-plan-ui';
}

export interface AuthToken {
  access_token: string,
  token_type: string,
  expires_in: number,
  refresh_token: string,
  scope: Array<string>
}
