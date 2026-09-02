import {Component, inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Auth} from '../../../core/services/auth';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {
  private authService = inject(Auth);
  private router = inject(Router);

  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";

  register(): void {
    this.authService.registerBuyer({
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password
    }).subscribe({
      next: response => {
        this.router.navigate(['/login']);
      },
      error: error => {
        console.log("Registration failed: " + error);
      }
    })
  }
}
