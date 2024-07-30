import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { appRoutes } from './app.routes';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReviewComponent } from './review/review.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { AppointmentService } from './services/appointment.service';
import { BookingComponent } from './booking/booking.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { BookingHistoryComponent } from './booking-history/booking-history.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    ReviewComponent,
    BookingComponent,
    AppointmentsComponent,
    BookingHistoryComponent
  
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [AppointmentService],
  bootstrap: [AppComponent]
})
export class MainModule { }




