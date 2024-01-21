import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../Services/AuthService/token-storage.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  storedToken: String | null | undefined;

  constructor(private token: TokenStorageService,private router: Router) { }


  ngOnInit(): void {
    this.storedToken = this.token.getToken();
    if (this.storedToken) {
    this.currentUser = this.token.getUser();
    }
  }
  signOut(): void {

    this.token.signOut();


    // Navigate to the login page after signing out
    this.router.navigate(['/login']);
  }
}


