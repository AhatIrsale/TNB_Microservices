import { Component, OnInit } from '@angular/core';
import { ClientService } from '../Services/Client/client.service';
@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  clients: any[] | undefined;
  newClient: any = {};
  editedClientId: string | null = null;

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.getAllClients();
  }

  getAllClients(): void {
    this.clientService.getAllClients().subscribe((data: any[]) => {
      this.clients = data;
    });
  }

  createClient(): void {
    this.clientService.createClient(this.newClient).subscribe(() => {
      this.newClient = {};
      this.getAllClients();
    });
  }

  editClient(id: string): void {
    this.editedClientId = id;
  }

  updateClient(client: any): void {
    this.clientService.updateClient(client.id, client).subscribe(() => {
      this.editedClientId = null;
      this.getAllClients();
    });
  }

  deleteClient(id: string): void {
    this.clientService.deleteClient(id).subscribe(() => {
      this.getAllClients();
    });
  }

  cancelEdit(): void {
    this.editedClientId = null;
  }

}
