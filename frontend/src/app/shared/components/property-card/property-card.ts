import {ChangeDetectorRef, Component, inject, Input, OnInit} from '@angular/core';
import {Property} from '../../../core/services/property';
import {CommonModule, DecimalPipe} from '@angular/common';
import {RouterLink} from '@angular/router';
import {Favorite} from '../../../core/services/favorite';
import {Auth} from '../../../core/services/auth';

@Component({
  selector: 'app-property-card',
  imports: [DecimalPipe, RouterLink, CommonModule],
  templateUrl: './property-card.html',
  styleUrl: './property-card.scss',
})
export class PropertyCard implements OnInit {
  private favoriteService = inject(Favorite);
  private cdr = inject(ChangeDetectorRef);
  private authService = inject(Auth);

  @Input({ required: true })
  property !: Property;

  isFavorite: boolean = false;
  favoriteError: string = '';
  isLoggedIn: boolean = false;

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  addFavorite(event: Event): void {

    // Prevent clicking the heart from triggering
    // any parent click behaviour.
    event.stopPropagation();

    this.favoriteError = '';

    this.favoriteService
      .addFavorite(this.property.id)
      .subscribe({
        next: () => {
          this.isFavorite = true;
          console.log('Added to favorites');
        },
        error: (error) => {
          if(error.status === 401 || error.status === 403) {
            this.favoriteError = "Please login to add favorite";
          } else if(error.status === 409) {
            this.isFavorite = true;
          } else if(error.status === 500) {
            this.favoriteError = "Something went wrong";
          }
          this.cdr.detectChanges();
          console.error('Failed to add favorite', error);
        }
      });
  }
}
