import { Component, OnInit } from '@angular/core';
import { TerrainService } from '../Services/Terrain/terrain.service';
import { CategorieService } from '../Services/Categorie/categorie.service';
import { ClientService } from '../Services/Client/client.service';

@Component({
  selector: 'app-terrain',
  templateUrl: './terrain.component.html',
  styleUrls: ['./terrain.component.css'],
})
export class TerrainComponent implements OnInit {
  terrainList: any[] | undefined;
  newTerrain: any = {};
  editedTerrainId: string | null = null;
  categories: any[] | undefined;
  clients: any[] | undefined;

  constructor(
    private terrainService: TerrainService,
    private categorieService: CategorieService,
    private clientService: ClientService
  ) {}

  ngOnInit(): void {
    this.getAllTerrain();
    this.getAllCategories();
    this.getAllClients();
  }

  getAllTerrain(): void {
    this.terrainService.getAllTerrains().subscribe((data: any[]) => {
      this.terrainList = data;
    });
  }

  getAllCategories(): void {
    this.categorieService.getAllCategories().subscribe((data: any[]) => {
      this.categories = data;
    });
  }

  getAllClients(): void {
    this.clientService.getAllClients().subscribe((data: any[]) => {
      this.clients = data;
    });
  }

  createTerrain(): void {
    this.terrainService.createTerrain(this.newTerrain).subscribe(() => {
      this.newTerrain = {};
      this.getAllTerrain();
    });
  }

  editTerrain(id: string): void {
    this.editedTerrainId = id;
  }

  updateTerrain(terrain: any): void {
    this.terrainService.updateTerrain(terrain.id, terrain).subscribe(() => {
      this.editedTerrainId = null;
      this.getAllTerrain();
    });
  }

  deleteTerrain(id: string): void {
    this.terrainService.deleteTerrain(id).subscribe(() => {
      this.getAllTerrain();
    });
  }

  cancelEdit(): void {
    this.editedTerrainId = null;
  }
}
