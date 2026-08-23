import {ChangeDetectorRef, Component, inject} from '@angular/core';
import {Favorite, FavoriteProperty} from '../../core/services/favorite';
import {DecimalPipe} from '@angular/common';


@Component({
  selector: 'app-favorites',
  imports: [DecimalPipe],
  templateUrl: './favorites.html',
  styleUrl: './favorites.scss',
})
export class Favorites {
  private favoriteService = inject(Favorite);
  private cdr = inject(ChangeDetectorRef);

  favorites: FavoriteProperty[] = [];

  ngOnInit(): void {

    this.favoriteService.getFavorites().subscribe({
      next: (response) => {
        this.favorites = response.content;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Failed to load favorites', error);
      }
    });
  }
}
