import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TauxService {
  private apiUrl = 'http://localhost:8888/SERVICE-TNB/api/taux'; // Remplacez l'URL par l'URL de votre backend

  constructor(private http: HttpClient) {}

  getAllTaux(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getTauxById(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  createTaux(taux: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, taux);
  }

  updateTaux(id: string, taux: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, taux);
  }

  deleteTaux(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
