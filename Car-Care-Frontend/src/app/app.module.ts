import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// import { HomeComponent } from './home/home.component';
// import { LoginComponent } from './login/login.component';
// import { SignupComponent } from './signup/signup.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
// import { ReviewComponent } from './review/review.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
// import { AppointmentService } from './services/appointment.service';
// import { BookingComponent } from './booking/booking.component';
// import { AppointmentsComponent } from './appointments/appointments.component';
// import { BookingHistoryComponent } from './booking-history/booking-history.component';
// import { UserService } from './services/user.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CarServicesUserComponent } from './car-services-user/car-services-user.component';
import { CarServicesAdminComponent } from './car-services-admin/car-services-admin.component'; // Ensure this is imported



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    // HomeComponent,
    // LoginComponent,
    // SignupComponent,
    // ReviewComponent,
    // BookingComponent,
    // AppointmentsComponent,
    // BookingHistoryComponent,
    CarServicesUserComponent,
    CarServicesAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }




