import { Component, OnInit } from '@angular/core';
import { TaxService } from '../Services/Tax/tax.service'; // Assurez-vous d'importer le service Taxe
import { TauxService } from '../Services/Taux/taux.service';
import { TerrainService } from '../Services/Terrain/terrain.service';
import { ClientService } from '../Services/Client/client.service';

@Component({
  selector: 'app-tax',
  templateUrl: './tax.component.html',
  styleUrls: ['./tax.component.css'],
})
export class TaxComponent implements OnInit {
  taxeList: any[] | undefined;
  newTaxe: any = {};
  editedTaxeId: string | null = null;
  clients: any[] | undefined;
  tauxList: any[] | undefined;
  terrainList: any[] | undefined;

  constructor(
    private taxeService: TaxService,
    private clientService: ClientService,
    private tauxService: TauxService,
    private terrainService: TerrainService
  ) {}

  ngOnInit(): void {
    this.getAllTaxe();
    this.getAllClients();
    this.getAllTaux();
    this.getAllTerrain();
  }

  getAllTaxe(): void {
    this.taxeService.getAllTaxes().subscribe((data: any[]) => {
      this.taxeList = data;
    });
  }

  getAllClients(): void {
    this.clientService.getAllClients().subscribe((data: any[]) => {
      this.clients = data;
    });
  }

  getAllTaux(): void {
    this.tauxService.getAllTaux().subscribe((data: any[]) => {
      this.tauxList = data;
    });
  }

  getAllTerrain(): void {
    this.terrainService.getAllTerrains().subscribe((data: any[]) => {
      this.terrainList = data;
    });
  }

  createTaxe(): void {
    this.taxeService.createTax(this.newTaxe).subscribe(() => {
      this.newTaxe = {};
      this.getAllTaxe();
    });
  }

  editTaxe(id: string): void {
    this.editedTaxeId = id;
  }

  updateTaxe(taxe: any): void {
    this.taxeService.updateTax(taxe.id, taxe).subscribe(() => {
      this.editedTaxeId = null;
      this.getAllTaxe();
    });
  }

  deleteTaxe(id: string): void {
    this.taxeService.deleteTax(id).subscribe(() => {
      this.getAllTaxe();
    });
  }

  cancelEdit(): void {
    this.editedTaxeId = null;
  }
}
