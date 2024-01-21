import { Component, OnInit } from '@angular/core';
import { TerrainService } from '../Services/Terrain/terrain.service';
import { TokenStorageService } from '../Services/AuthService/token-storage.service';

@Component({
  selector: 'app-terrain-taxes',
  templateUrl: './terrain-taxes.component.html',
  styleUrls: ['./terrain-taxes.component.css']
})
export class TerrainTaxesComponent implements OnInit {
  terrains: any[] | undefined;
  taxes: any[] | undefined;
  taxesVisible: boolean = false;
  selectedTerrain: any;

  currentUser: any;
  storedToken: String | null | undefined;

  constructor(private terrainService: TerrainService, private token: TokenStorageService) { }

  ngOnInit(): void {

    this.storedToken = this.token.getToken();
    if (this.storedToken) {
    this.currentUser = this.token.getUser();
    this.loadTerrains();
   }
  }

  loadTerrains(): void {
    this.terrainService.getTerrainsbyUser(this.currentUser.username).subscribe(
      (data: any[]) => {
        this.terrains = data;
      },
      error => {
        console.error('Erreur lors du chargement des terrains', error);
      }
    );
  }

  afficherTaxes(terrain: any): void {
    this.selectedTerrain = terrain;
    this.taxes = terrain.taxes;
    this.taxesVisible = true;
  }
}
