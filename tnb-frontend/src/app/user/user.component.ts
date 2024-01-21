// user.component.ts
import { Component, OnInit } from '@angular/core';
import { UserService } from '../Services/UserService/useruser.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  users: any[] | undefined;
  selectedUser: any = {};
  newUser: any = {};
  isEditing: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
    });
  }

  viewUserDetails(user: any): void {
    this.selectedUser = user;
    this.isEditing = false;
  }

  editUser(user: any): void {
    this.selectedUser = { ...user };
    this.isEditing = true;
  }

  signupUser(): void {
    this.userService.signupUser(this.newUser).subscribe(() => {
      this.loadUsers();
      this.newUser = {};
    });
  }

  updateUser(): void {
    this.userService.updateUser(this.selectedUser.id, this.selectedUser).subscribe(() => {
      this.loadUsers();
      this.selectedUser = {};
      this.isEditing = false;
    });
  }

  deleteUser(id: number): void {
    this.userService.deleteUser(id).subscribe(() => {
      this.loadUsers();
    });
  }
}
