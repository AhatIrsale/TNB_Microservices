import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';



@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private baseUrl = 'http://localhost:8888/SERVICE-TNB/api/client'; // Update with your backend URL

  constructor(private http: HttpClient,private router: Router) {}

  getAllClients(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }

  getClientById(id: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  createClient(client: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}`, client);
  }

  updateClient(id: string, client: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, client);
  }

  deleteClient(id: string): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }
}
