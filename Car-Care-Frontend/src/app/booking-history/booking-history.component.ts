import { Component, OnInit, ElementRef, Renderer2 } from '@angular/core';
import { AppointmentService, Appointment } from '../appointments/appointment.service';

@Component({
  selector: 'app-booking-history',
  templateUrl: './booking-history.component.html',
  styleUrls: ['./booking-history.component.css']
})
export class BookingHistoryComponent implements OnInit {
  pastAppointments: Appointment[] = [];

  constructor(
    private appointmentService: AppointmentService,
    private el: ElementRef,
    private renderer: Renderer2
  ) {}

  ngOnInit(): void {
    this.loadPastAppointments();
    this.displayPastAppointments();
  }

  loadPastAppointments(): void {
    this.pastAppointments = this.appointmentService.getPastAppointments();
  }

  displayPastAppointments(): void {
    const container = this.el.nativeElement.querySelector('.booking-history-container');
    
    while (container.firstChild) {
      container.removeChild(container.firstChild);
    }

    if (this.pastAppointments.length === 0) {
      const noAppointmentsMessage = this.renderer.createText('No past appointments found.');
      const p = this.renderer.createElement('p');
      this.renderer.appendChild(p, noAppointmentsMessage);
      this.renderer.appendChild(container, p);
    } else {
      const ul = this.renderer.createElement('ul');
      this.pastAppointments.forEach(appointment => {
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

        this.renderer.appendChild(li, strong);
        this.renderer.appendChild(li, dateP);
        this.renderer.appendChild(li, timeP);
        this.renderer.appendChild(ul, li);
      });
      this.renderer.appendChild(container, ul);
    }
  }
}
