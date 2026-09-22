import { Component, inject, OnInit } from '@angular/core';
import { SellerDashboardResponse, SellerService } from '../../../core/services/seller';

@Component({
  selector: 'app-seller-dashboard',
  imports: [],
  templateUrl: './seller-dashboard.html',
  styleUrl: './seller-dashboard.scss',
})
export class SellerDashboard implements OnInit{
  private sellerService = inject(SellerService)

  dashboard?: SellerDashboardResponse;

  ngOnInit(): void {
    this.loadDashboard();
  }

    loadDashboard(): void {
    this.sellerService.getDashboard().subscribe({
      next: (response) => {
        this.dashboard = response;
      },
      error: (error) => {
        console.error('Failed to load seller dashboard', error);
      }
    });
  }

}
