import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './Services/AuthService/token-storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit  {
  title = 'tnb-frontend';
  currentUser: any;
  storedToken: String | null | undefined;

  constructor(private token: TokenStorageService,private router: Router) {

  }




  logout(): void {
    this.token.signOut();

    this.router.navigate(['/login']);


  }

  ngOnInit(): void {
    this.storedToken = this.token.getToken();
    if (this.storedToken) {
    this.currentUser = this.token.getUser();
    }
  }

}
