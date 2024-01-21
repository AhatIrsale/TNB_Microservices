import { CategorieService } from './../Services/Categorie/categorie.service';
import { Component, OnInit } from '@angular/core';
import { TauxService } from '../Services/Taux/taux.service';
import { ClientService } from '../Services/Client/client.service'; // Assurez-vous d'importer le service Client

@Component({
  selector: 'app-taux',
  templateUrl: './taux.component.html',
  styleUrls: ['./taux.component.css'],
})
export class TauxComponent implements OnInit {
  tauxList: any[] | undefined;
  newTaux: any = {};
  editedTauxId: string | null = null;
  clients: any[] | undefined;
  categories: any[] | undefined;

  constructor(private tauxService: TauxService, private clientService: ClientService, private CategorieService:CategorieService) {}

  ngOnInit(): void {
    this.getAllTaux();
    this.getAllClients();
    this.getAllCategories();
  }

  getAllTaux(): void {
    this.tauxService.getAllTaux().subscribe((data: any[]) => {
      this.tauxList = data;
    });
  }

  getAllClients(): void {
    this.clientService.getAllClients().subscribe((data: any[]) => {
      this.clients = data;
    });
  }
  getAllCategories(): void {
     this.CategorieService.getAllCategories().subscribe((data: any[]) => {
       this.categories = data;
    });
  }

  createTaux(): void {
    this.tauxService.createTaux(this.newTaux).subscribe(() => {
      this.newTaux = {};
      this.getAllTaux();
    });
  }

  editTaux(id: string): void {
    this.editedTauxId = id;
  }

  updateTaux(taux: any): void {
    this.tauxService.updateTaux(taux.id, taux).subscribe(() => {
      this.editedTauxId = null;
      this.getAllTaux();
    });
  }

  deleteTaux(id: string): void {
    this.tauxService.deleteTaux(id).subscribe(() => {
      this.getAllTaux();
    });
  }

  cancelEdit(): void {
    this.editedTauxId = null;
  }
}
