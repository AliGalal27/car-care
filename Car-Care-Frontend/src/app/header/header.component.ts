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

  goToServices() {
    this.router.navigate(['/car-services-user']);
  }
  goToServices_admin() {
    this.router.navigate(['/car-services-admin']);
  }
  goToContact(): void {
    this.router.navigate(['/contact']);
  }
  goToBokings(): void {
    this.router.navigate(['/booking']);
  }
  goToAppointments(): void {
    this.router.navigate(['/appointments']);
  }
  goToBookingsHistory(): void {
    this.router.navigate(['/booking-history']);
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
