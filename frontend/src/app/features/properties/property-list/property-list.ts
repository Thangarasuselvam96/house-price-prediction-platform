import {ChangeDetectorRef, Component, inject, OnInit} from '@angular/core';
import {Property, PropertyService} from '../../../core/services/property';
import {PropertyCard} from '../../../shared/components/property-card/property-card';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-property-list',
  imports: [PropertyCard, FormsModule],
  templateUrl: './property-list.html',
  styleUrl: './property-list.scss',
})
export class PropertyList implements OnInit {
  private propertyService = inject(PropertyService);
  private cdr = inject(ChangeDetectorRef);
  properties: Property[] = [];

  page = 0;
  size = 9;

  totalPages = 0;

  city = '';
  propertyType = '';
  minPrice?: number;
  maxPrice?: number;
  listingType = '';
  sortBy = 'createdAt';
  direction = 'desc';
  sortOption = '';

  ngOnInit(): void {
    this.loadProperties();
  }

  loadProperties(): void {
    this.propertyService.getProperties(this.page,
      this.size,
      this.city,
      this.minPrice,
      this.maxPrice,
      this.propertyType,
      this.sortBy,
      this.direction)
    .subscribe({
      next: response => {
        this.properties = response.content;
        this.totalPages = response.totalPages;
        this.cdr.detectChanges();
      },
      error: error => {
        console.error('Failed to load properties', error);
      }
    })
  }

  nextPage(): void {
    if(this.page < this.totalPages - 1) {
      this.page++;
      this.loadProperties();
    }
  }

  previousPage(): void {
    if(this.page > 0) {
      this.page--;
      this.loadProperties();
    }
  }

  search(): void {
    this.page = 0;
    this.loadProperties();
  }

  applySort(): void {
    this.page = 0;

    switch (this.sortOption) {
      case 'priceLow':
        this.sortBy = 'price';
        this.direction = 'asc';
        break;
      case 'priceHigh':
        this.sortBy = 'price';
        this.direction = 'desc';
        break;
      default:
        this.sortBy = 'createdAt';
        this.direction = 'desc';
    }

    this.loadProperties();
  }
}
