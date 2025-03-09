import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QuoteService } from '../../services/quote.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quote-generator',
  templateUrl: './quote-generator.component.html',
  styleUrls: ['./quote-generator.component.scss']
})
export class QuoteGeneratorComponent {
  quoteForm: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private quoteService: QuoteService,
    private router: Router
  ) {
    this.quoteForm = this.fb.group({
      customerName: ['', Validators.required],
      customerAge: ['', [Validators.required, Validators.min(18)]],
      customerEmail: ['', [Validators.required, Validators.email]],
      customerPhone: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      destinationCity: ['', Validators.required],
      numberOfTravelers: ['', [Validators.required, Validators.min(1)]],
      activities: [[]]
    });
  }

  onSubmit(): void {
    if (this.quoteForm.valid) {
      this.quoteService.createQuote(this.quoteForm.value).subscribe(
        (response) => {
          this.successMessage = 'Quote generated successfully!';
          this.quoteForm.reset();
          setTimeout(() => {
            this.router.navigate(['/dashboard']);
          }, 2000);
        },
        (error) => {
          this.errorMessage = 'Error generating quote. Please try again.';
        }
      );
    }
  }
}
