import { Component } from '@angular/core';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent {
  rating: number = 0;

  rate(star: number): void {
    this.rating = star;
  }

  onSubmit(): void {
    const name = (document.getElementById('name') as HTMLInputElement).value;
    const service = (document.getElementById('service') as HTMLSelectElement).value;
    const reviewText = (document.getElementById('reviewText') as HTMLTextAreaElement).value;

    if (this.isFormValid(name, service, reviewText)) {
      // Post review logic
      console.log('Review submitted:', {
        name,
        service,
        reviewText,
        rating: this.rating
      });
      alert('Thank you for your review!');
      
      // Reset the form fields
      this.resetForm();
    } else {
      alert('Please fill out all required fields.');
    }
  }

  isFormValid(name: string, service: string, reviewText: string): boolean {
    return name.trim() !== '' && service !== 'None' && reviewText.trim() !== '';
  }

  resetForm(): void {
    // Reset the rating
    this.rating = 0;

    // Clear input fields by using native elements
    (document.getElementById('name') as HTMLInputElement).value = '';
    (document.getElementById('service') as HTMLSelectElement).value = 'None';
    (document.getElementById('reviewText') as HTMLTextAreaElement).value = '';
  }
}
