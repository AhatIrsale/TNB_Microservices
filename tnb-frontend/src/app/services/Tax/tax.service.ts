import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaxService {
  private baseUrl = 'http://localhost:8888/SERVICE-TNB/api/taxe-tnb'; // Update with your backend URL

  constructor(private http: HttpClient) {}

  getAllTaxes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }

  getTaxById(id: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  createTax(tax: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}`, tax);
  }

  updateTax(id: string, tax: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, tax);
  }

  deleteTax(id: string): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }
}
