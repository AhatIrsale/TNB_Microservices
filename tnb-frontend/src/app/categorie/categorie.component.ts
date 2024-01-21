// categorie.component.ts

import { Component, OnInit } from '@angular/core';
import { CategorieService } from '../Services/Categorie/categorie.service'; // Assurez-vous d'ajuster le chemin selon votre structure

@Component({
  selector: 'app-categorie',
  templateUrl: './categorie.component.html',
  styleUrls: ['./categorie.component.css']
})
export class CategorieComponent implements OnInit {
  categories: any[] | undefined;
  newCategorie: any = {};
  editedCategorieId: string | null = null;

  constructor(private categorieService: CategorieService) {}

  ngOnInit(): void {
    this.getAllCategories();
  }

  getAllCategories(): void {
    this.categorieService.getAllCategories().subscribe((data: any[]) => {
      this.categories = data;
    });
  }

  createCategorie(): void {
    this.categorieService.createCategory(this.newCategorie).subscribe(() => {
      this.newCategorie = {};
      this.getAllCategories();
    });
  }

  editCategorie(id: string): void {
    this.editedCategorieId = id;
  }

  updateCategorie(categorie: any): void {
    this.categorieService.updateCategory(categorie.id, categorie).subscribe(() => {
      this.editedCategorieId = null;
      this.getAllCategories();
    });
  }

  deleteCategorie(id: string): void {
    this.categorieService.deleteCategory(id).subscribe(() => {
      this.getAllCategories();
    });
  }

  cancelEdit(): void {
    this.editedCategorieId = null;
  }

}
