import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private router: Router) { }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  goToSignup() {
    this.router.navigate(['/signup']);
  }

  goToHome() {
    this.router.navigate(['/']);
  }

  goToServices() {
    this.router.navigate(['/car-services-user']);
  }
  goToServices_admin() {
    this.router.navigate(['/car-services-admin']);
  }

  goToContact() {
    this.router.navigate(['/contact']);
  }

  goToReview() {
    this.router.navigate(['/review']);
  }
}
