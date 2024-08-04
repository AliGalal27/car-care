import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, Input} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';


interface CarService {
  serviceid: number;
  service_name: string;
  description: string;
  serviceType: string;
  price: number;
  image_url:string;
  editMode?:boolean
}


@Component({
  selector: 'app-car-services-admin',
  templateUrl: './car-services-admin.component.html',
  styleUrl: './car-services-admin.component.css'
})
export class CarServicesAdminComponent {


  @Input() carService: any;
  editing:boolean=false;
  carServices: CarService[] = [];
  allCarServices: CarService[] = [];  // Keep all services to reset filter
  serviceTypes: string[] = []; // Array to hold service types
  selectedFile: File | null = null;
  // addServiceForm: FormGroup;
  isAddServiceFormVisible = false;
  newService: any = { 
    name: '', 
    description: '',
    serviceType:'',
    price:0,
    image_url: '' 
    };




  constructor(private http: HttpClient,private router: Router) { 
    // this.addServiceForm = this.fb.group({
    //   name: ['', Validators.required],
    //   description: ['', Validators.required],
    //   image_url: ['', Validators.required]
    // });
  }

  ngOnInit(): void {
    this.loadAllServices();
    this.loadServiceTypes();

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
  loadServiceTypes(): void {
    this.http.get<string[]>('http://localhost:8080/CarServices/types').subscribe({
      next: (data) => {
        this.serviceTypes = data;
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

  toggleEditing(service:any):void{
    service.editMode = !service.editMode;
  }

  saveChanges(serviceid:number,service: any): void {
    const updatedService = {
      service_name: service.service_name,
      description: service.description,
      serviceType: service.serviceType,
      price: service.price,
      image_url: service.imageUrl
    };

  

    this.http.put(`http://localhost:8080/CarServices/${serviceid}`, updatedService)
    .subscribe({
      next: response => {
        service.editMode = false;
        console.log('Service updated successfully', response);
      },
      error: (error: HttpErrorResponse) => {
        console.error('There was an error!', error);
      }
    });
  }
  //upload or update image    
  onFileSelected(event: Event,carService:CarService): void {
    const input = event.target as HTMLInputElement;
    if (input?.files?.length) {
      this.selectedFile = input.files[0];
    }
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      this.http.post(`http://localhost:8080/CarServices/upload/${carService.serviceid}`, formData)
        .subscribe(
          response => {
            console.log('Upload successful', response);
            // Optionally, update the UI to reflect the new image
            this.loadAllServices();

          },
          error => {
            console.error('Upload failed', error);
          }
        );
        this.loadAllServices();
    }
    

  }

  deleteService(id: number): void {
    if (confirm('Are you sure you want to delete this service?')) {
      this.http.delete<void>(`http://localhost:8080/CarServices/${id}`).subscribe(
        () => {
          this.allCarServices = this.allCarServices.filter(service => service.serviceid !== id); // Remove the deleted service from the list
          this.loadAllServices();
        },
        error => {
          console.error('Error deleting service', error);
        }
      );
    }
  }

  showAddServiceForm(): void {
    this.isAddServiceFormVisible = true;
  }

  addService(): void {
    this.http.post<any>('http://localhost:8080/CarServices', this.newService).subscribe(
      response => {
        this.allCarServices.push(this.newService); // Add the new service to the list
        this.newService = { name: '', 
          description: '',
          serviceType:'',
          price:0,
          image_url: ''  }; // Reset the form
        console.log("Service added successfully");
      },
      error => {
        console.error('Error adding service', error);
      }
    );
  }

}
