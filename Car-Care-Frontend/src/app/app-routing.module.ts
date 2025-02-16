import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ReviewComponent } from './review/review.component';
import { BookingComponent } from './booking/booking.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { BookingHistoryComponent } from './booking-history/booking-history.component';
import { ContactComponent } from './contact/contact.component';
import { CarServicesUserComponent } from './car-services-user/car-services-user.component';
import { CarServicesAdminComponent } from "./car-services-admin/car-services-admin.component";
import { ResetPasswordComponent } from "./reset-password/reset-password.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'review', component: ReviewComponent },
  { path: 'booking', component: BookingComponent },
  { path: 'appointments', component: AppointmentsComponent },
  { path: 'booking-history', component: BookingHistoryComponent },
  { path: 'car-services-user', component: CarServicesUserComponent },
  { path: 'car-services-admin', component: CarServicesAdminComponent }


];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }