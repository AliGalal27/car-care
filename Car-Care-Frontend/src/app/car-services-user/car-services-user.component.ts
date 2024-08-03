import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';


interface CarService {
  service_id: number;
  service_name: string;
  description: string;
  serviceType: string;
  price: number;
  image_url:string;
}

@Component({
  selector: 'app-car-services-user',
  templateUrl: './car-services-user.component.html',
  styleUrls: ['./car-services-user.component.css']
})
export class CarServicesUserComponent implements OnInit {
  carServices: CarService[] = [];
  allCarServices: CarService[] = [];  // Keep all services to reset filter

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    this.loadAllServices();
  }

  loadAllServices(): void {
    this.http.get<CarService[]>('http://localhost:8080/CarServices').subscribe({
      next: (data) => {
        this.carServices = data;
        this.allCarServices = data;
      },
      error: (error: HttpErrorResponse) => {
        console.error('There was an error!', error);
      }
    });
  }

  onServiceTypeChange(event: any): void {
    const selectedType = event.target.value;
    if (selectedType === '') {
      // Reset to all services if no specific type is selected
      this.carServices = this.allCarServices;
    } else {
      this.http.get<CarService[]>(`http://localhost:8080/CarServices/type/${selectedType}`).subscribe({
        next: (data) => {
          this.carServices = data;
        },
        error: (error: HttpErrorResponse) => {
          console.error('There was an error!', error);
        }
      });
    }
  }

  bookService(serviceId: number): void {
    this.router.navigate(['/booking', serviceId]);

  }


}
