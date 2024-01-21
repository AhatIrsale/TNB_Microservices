import { Component, OnInit } from '@angular/core';
import { TerrainService } from '../Services/Terrain/terrain.service';
import { TokenStorageService } from '../Services/AuthService/token-storage.service';

@Component({
  selector: 'app-user-terains',
  templateUrl: './user-terains.component.html',
  styleUrls: ['./user-terains.component.css']
})
export class UserTerainsComponent implements OnInit  {
  terrains: any[] | undefined;
  currentUser: any;
  storedToken: String | null | undefined;


  constructor(private terrainService: TerrainService,private token: TokenStorageService) { }

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


}
