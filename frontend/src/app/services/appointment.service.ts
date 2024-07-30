import { Injectable } from '@angular/core';

export interface Appointment {
  service: string;
  date: string;
  time: string;
}

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private appointments: Appointment[] = [];
  private pastAppointments: Appointment[] = [];

  constructor() {
    this.loadAppointments();
  }

  addAppointment(appointment: Appointment): void {
    this.appointments.push(appointment);
    this.saveAppointments();
  }

  getAppointments(): Appointment[] {
    this.updateAppointments();
    return this.appointments;
  }

  getPastAppointments(): Appointment[] {
    this.updateAppointments();
    return this.pastAppointments;
  }

  cancelAppointment(index: number): void {
    if (index >= 0 && index < this.appointments.length) {
      // Move the canceled appointment to past appointments
      const canceledAppointment = this.appointments.splice(index, 1)[0];
      this.pastAppointments.push(canceledAppointment);
      this.saveAppointments();
      this.savePastAppointments();
    }
  }

  rescheduleAppointment(index: number, newDate: string, newTime: string): void {
    if (index >= 0 && index < this.appointments.length) {
      this.appointments[index].date = newDate;
      this.appointments[index].time = newTime;
      this.saveAppointments();
    }
  }

  private updateAppointments(): void {
    const now = new Date();
    const newPastAppointments: Appointment[] = []; // Temporary array for past appointments

    this.appointments = this.appointments.filter(appointment => {
      const appointmentDateTime = new Date(`${appointment.date}T${appointment.time}`);
      if (appointmentDateTime < now) {
        newPastAppointments.push(appointment); // Collect past appointments
        return false; // Remove from current appointments
      }
      return true; // Keep future appointments
    });

    // Update pastAppointments
    this.pastAppointments = [...this.pastAppointments, ...newPastAppointments]; // Merge new past appointments

    // Save to local storage
    this.saveAppointments();
    this.savePastAppointments();
  }

  private saveAppointments(): void {
    localStorage.setItem('appointments', JSON.stringify(this.appointments));
  }

  private savePastAppointments(): void {
    localStorage.setItem('pastAppointments', JSON.stringify(this.pastAppointments));
  }

  private loadAppointments(): void {
    const appointments = localStorage.getItem('appointments');
    if (appointments) {
      this.appointments = JSON.parse(appointments);
    }
    const pastAppointments = localStorage.getItem('pastAppointments');
    if (pastAppointments) {
      this.pastAppointments = JSON.parse(pastAppointments);
    }
  }
}
