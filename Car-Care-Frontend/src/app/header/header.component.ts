import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private router: Router) {}

  goToHome(): void {
    this.router.navigate(['/']);
  }

  goToServices(): void {
    this.router.navigate(['/services']);
  }

  goToContact(): void {
    this.router.navigate(['/contact']);
  }

  goToReview(): void {
    this.router.navigate(['/review']);
  }

  goToLogin(): void {
    this.router.navigate(['/login']);
  }

  goToSignup(): void {
    this.router.navigate(['/signup']);
  }
}
