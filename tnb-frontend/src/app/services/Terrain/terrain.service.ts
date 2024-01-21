import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TerrainService {

  private baseUrl = 'http://localhost:8888/SERVICE-TNB/api/terrain'; // Update with your backend URL

  constructor(private http: HttpClient) {}

  getAllTerrains(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }

  getTerrainById(id: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  createTerrain(terrain: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}`, terrain);
  }

  updateTerrain(id: string, terrain: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, terrain);
  }

  deleteTerrain(id: string): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }

  getTerrainsbyUser(username: string): Observable<any[]> {
    const url = `${this.baseUrl}/t/user/${username}`;

    return this.http.get<any[]>(url);
  }

}
