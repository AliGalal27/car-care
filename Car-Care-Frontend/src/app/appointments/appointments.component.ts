import { Component, OnInit, ElementRef, Renderer2 } from '@angular/core';
import { AppointmentService, Appointment } from '../appointments/appointment.service'

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {
  appointments: Appointment[] = [];

  constructor(
    private appointmentService: AppointmentService,
    private el: ElementRef,
    private renderer: Renderer2
  ) {}

  ngOnInit(): void {
    this.loadAppointments();
    this.displayAppointments();
  }

  loadAppointments(): void {
    this.appointments = this.appointmentService.getAppointments();
  }

  displayAppointments(): void {
    const container = this.el.nativeElement.querySelector('.appointments-container');

    while (container.firstChild) {
      container.removeChild(container.firstChild);
    }

    if (this.appointments.length === 0) {
      const noAppointmentsMessage = this.renderer.createText('No appointments found.');
      const p = this.renderer.createElement('p');
      this.renderer.appendChild(p, noAppointmentsMessage);
      this.renderer.appendChild(container, p);
    } else {
      const ul = this.renderer.createElement('ul');
      this.appointments.forEach((appointment, index) => {
        const li = this.renderer.createElement('li');

        const strong = this.renderer.createElement('strong');
        const serviceText = this.renderer.createText(appointment.service);
        this.renderer.appendChild(strong, serviceText);

        const dateP = this.renderer.createElement('p');
        const dateText = this.renderer.createText(`Date: ${appointment.date}`);
        this.renderer.appendChild(dateP, dateText);

        const timeP = this.renderer.createElement('p');
        const timeText = this.renderer.createText(`Time: ${appointment.time}`);
        this.renderer.appendChild(timeP, timeText);

        const cancelButton = this.renderer.createElement('button');
        const cancelButtonText = this.renderer.createText('Cancel');
        this.renderer.appendChild(cancelButton, cancelButtonText);
        this.renderer.listen(cancelButton, 'click', () => this.confirmCancelAppointment(index));

        const rescheduleButton = this.renderer.createElement('button');
        const rescheduleButtonText = this.renderer.createText('Reschedule');
        this.renderer.appendChild(rescheduleButton, rescheduleButtonText);
        this.renderer.listen(rescheduleButton, 'click', () => this.rescheduleAppointment(index));

        this.renderer.appendChild(li, strong);
        this.renderer.appendChild(li, dateP);
        this.renderer.appendChild(li, timeP);
        this.renderer.appendChild(li, cancelButton);
        this.renderer.appendChild(li, rescheduleButton);
        this.renderer.appendChild(ul, li);
      });
      this.renderer.appendChild(container, ul);
    }
  }

  confirmCancelAppointment(index: number): void {
    const confirmed = window.confirm('Are you sure you want to cancel this appointment?');
    if (confirmed) {
      this.cancelAppointment(index);
    }
  }

  cancelAppointment(index: number): void {
    this.appointmentService.cancelAppointment(index);
    this.loadAppointments();
    this.displayAppointments();
  }

  rescheduleAppointment(index: number): void {
    const newDate = prompt('Enter new date (YYYY-MM-DD):', this.appointments[index].date);
    const newTime = prompt('Enter new time (HH:MM):', this.appointments[index].time);

    if (newDate && newTime) {
      const now = new Date();
      const [selectedHours, selectedMinutes] = newTime.split(':').map(Number);
      const selectedDateTime = new Date(newDate);
      selectedDateTime.setHours(selectedHours, selectedMinutes);

      // Check if the new appointment time is valid
      if (selectedDateTime > now) {
        this.appointmentService.rescheduleAppointment(index, newDate, newTime);
        this.loadAppointments();
        this.displayAppointments();
      } else {
        alert('Invalid date or time. Please choose a future date and time.');
      }
    }
  }
}
