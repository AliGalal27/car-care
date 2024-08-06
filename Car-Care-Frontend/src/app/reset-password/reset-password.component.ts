import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'],
  standalone: true,
  imports: [FormsModule]
})
export class ResetPasswordComponent {
  email = '';
  verificationCode = '';
  newPassword = '';

  onSubmit() {
    console.log('Form submitted', this.email, this.verificationCode, this.newPassword);
  }
}
