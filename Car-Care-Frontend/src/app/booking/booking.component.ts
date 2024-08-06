import { Component } from '@angular/core';
import { AppointmentService, Appointment } from '../appointments/appointment.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {
  selectedService: string = 'Service Name'; // Replace with actual service name logic
  confirmationMessage: string = '';
  minDate: string = this.getMinDate();
  minTime: string = this.getMinTime();

  constructor(private appointmentService: AppointmentService) {}

  getMinDate(): string {
    const today = new Date();
    const year = today.getFullYear();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

  getMinTime(): string {
    const now = new Date();
    const hours = ('0' + now.getHours()).slice(-2);
    const minutes = ('0' + now.getMinutes()).slice(-2);
    return `${hours}:${minutes}`;
  }

  onSubmit(dateValue: string, timeValue: string): void {
    if (this.isValidDate(dateValue) && this.isValidTime(timeValue, dateValue)) {
      this.confirmationMessage = 'Booking confirmed';
      const appointment: Appointment = {
        service: this.selectedService,
        date: dateValue,
        time: timeValue
      };
      this.appointmentService.addAppointment(appointment);
      console.log('Booking Details:');
      console.log(`Service: ${this.selectedService}`);
      console.log(`Date: ${dateValue}`);
      console.log(`Time: ${timeValue}`);
    } else {
      this.confirmationMessage = 'Invalid date or time selected';
      console.log('Invalid booking attempt');
    }
  }

  isValidDate(dateValue: string): boolean {
    return new Date(dateValue) >= new Date(this.minDate);
  }

  isValidTime(timeValue: string, dateValue: string): boolean {
    const now = new Date();
    const [selectedHours, selectedMinutes] = timeValue.split(':').map(Number);
    const selectedDateTime = new Date(dateValue);
    selectedDateTime.setHours(selectedHours, selectedMinutes);

    if (dateValue === this.minDate) {
      return selectedDateTime >= now;
    }

    return true;
  }
}
