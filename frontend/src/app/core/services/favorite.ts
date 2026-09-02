import { Service, inject } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface FavoriteProperty {
  propertyId: number;
  city: string;
  price: number;
  title: string;
  propertyType: string;
  favoriteDate: string;
}

export interface PageResponse<T> {
  content: T[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

@Service()
export class Favorite {
  private http = inject(HttpClient);
  private readonly apiUrl = "http://localhost:8080/api/v1/favorites";

  addFavorite(propertyId: Number): Observable<any> {
    return this.http.post<void>(`${this.apiUrl}/${propertyId}`,{}, {
      headers: {
        Authorization: `Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJidXllckBnbWFpbC5jb20iLCJpYXQiOjE3ODcxNjIyNzMsImV4cCI6MTc4NzE2NTg3M30.Yi9UuOvgqE1fft3_SRfWWjGtZy0qFzb5IJ4EDLrqpqaLEcT-ow4XtFLbnj_vV4Jz`
      }
    });
  }

  getFavorites(
    page: number = 0,
    size: number = 10
  ): Observable<PageResponse<FavoriteProperty>> {

    return this.http.get<PageResponse<FavoriteProperty>>(`${this.apiUrl}?page=${page}&size=${size}`);
  }
}
