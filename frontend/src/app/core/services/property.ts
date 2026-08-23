import { Service } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';


export interface Property {
  id: number;
  title: string;
  description: string;
  propertyType: string;
  listingType: string;
  price: number;
  areaSqft: number;
  city: string;
  state: string;
  pincode: string;
  listingStatus: string;
}

@Injectable({
  providedIn: 'root'
})
export class PropertyService {
  private http = inject(HttpClient)
  private readonly apiUrl = 'http://localhost:8080/api/v1/properties';

  getProperties(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  getPropertyById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}
