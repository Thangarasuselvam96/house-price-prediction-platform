import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";

export interface SellerDashboardResponse {
  totalProperties: number;
  activeProperties: number;
  soldProperties: number;
}

@Injectable({
  providedIn: 'root'
})
export class SellerService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/v1/seller';

  getDashboard(): Observable<SellerDashboardResponse> {
    return this.http.get<SellerDashboardResponse>(`${this.apiUrl}/dashboard`)
  }
}