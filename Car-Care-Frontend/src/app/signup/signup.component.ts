import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  standalone: true,
  imports: [FormsModule]
})
export class SignupComponent {
  username = '';
  email = '';
  password = '';

  onSubmit() {
    console.log('Form submitted', this.username, this.email, this.password);
  }
}
