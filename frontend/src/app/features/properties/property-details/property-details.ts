import {ChangeDetectorRef, Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Property, PropertyService} from '../../../core/services/property';
import {DecimalPipe} from '@angular/common';
import {Favorite} from '../../../core/services/favorite';

@Component({
  selector: 'app-property-details',
  imports: [DecimalPipe],
  templateUrl: './property-details.html',
  styleUrl: './property-details.scss',
})
export class PropertyDetails implements OnInit {
  private route = inject(ActivatedRoute);
  private propertyService = inject(PropertyService)
  private FavoriteService = inject(Favorite)
  private cdr = inject(ChangeDetectorRef);

  property?: Property;


  ngOnInit(): void {
    const propertyId = Number(this.route.snapshot.paramMap.get('id'));

    this.propertyService.getPropertyById(propertyId)
      .subscribe({
        next : property => {
          this.property = property;
          this.cdr.detectChanges();
        },
        error: err => {
          console.error('Failed to load property', err);
        }
      })
  }

  addFavorite(): void {
    if(!this.property) {
      return;
    }
    this.FavoriteService.addFavorite(this.property.id)
      .subscribe({
        next: property => {
          console.log("Property added to favorites");
        },
        error: err => {
          console.error('Failed to add property', err);
        }
      })
  }

}
