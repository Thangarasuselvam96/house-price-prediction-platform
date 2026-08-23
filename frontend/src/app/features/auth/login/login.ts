import {Component, inject} from '@angular/core';
import {Auth} from '../../../core/services/auth';
import {Router} from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  private authService = inject(Auth)
  private router = inject(Router)

  email = '';
  password = '';

  login() {
    this.authService.login({email: this.email, password: this.password})
      .subscribe({
        next: result => {
          this.router.navigate(['/']);
        },
        error: err => {
          console.log('Login failed', err);
        }
      })
  }
}
