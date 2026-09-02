import {Component, inject} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {Auth} from '../../../core/services/auth';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar {
  private authService = inject(Auth)
  private router = inject(Router);

  get isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
