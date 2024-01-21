import { Component, OnInit } from '@angular/core';
import { UserService } from '../Services/AuthService/user.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css'],
  providers: [UserService],
})
export class BoardAdminComponent implements OnInit {
  content?: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAdminBoard().subscribe(
      (data : any) => {
        this.content = data;
      },
      (err : any) => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }
}
