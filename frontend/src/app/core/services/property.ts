import { Service } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
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

  getProperties(page: number = 0 , size: number = 9, city?: string, minPrice?: number, maxPrice?: number, propertyType?:string, sortBy:string = 'createdAt', direction:string = 'desc'): Observable<any> {
    let params = new HttpParams();
    params.set('page', page);
    params.set('size', size);
    params.set('sortBy', sortBy);
    params.set('direction', direction);

    if(city) {
      params = params.set('city', city);
    }
    if(minPrice !== undefined) {
      params = params.set('minPrice', minPrice);
    }
    if(maxPrice !== undefined) {
      params = params.set('maxPrice', maxPrice);
    }
    if(propertyType) {
      params = params.set('propertyType', propertyType);
    }
    return this.http.get<any>(this.apiUrl, {params});
  }

  getPropertyById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}
