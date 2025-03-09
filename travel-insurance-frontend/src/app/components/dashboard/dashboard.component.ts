import { Component, OnInit } from '@angular/core';
import { QuoteService } from '../../services/quote.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  quotes: any[] = [];
  brokerId: string = '';

  constructor(
    private quoteService: QuoteService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Get broker ID from token or session
    this.brokerId = this.getBrokerIdFromToken();
    this.loadQuotes();
  }

  loadQuotes(): void {
    this.quoteService.getQuotesByBroker(this.brokerId).subscribe(
      (quotes) => {
        this.quotes = quotes;
      },
      (error) => {
        console.error('Error loading quotes:', error);
      }
    );
  }

  updateQuoteStatus(quoteId: string, status: string): void {
    this.quoteService.updateQuoteStatus(quoteId, status).subscribe(
      () => {
        this.loadQuotes();
      },
      (error) => {
        console.error('Error updating quote status:', error);
      }
    );
  }

  private getBrokerIdFromToken(): string {
    // Implement logic to extract broker ID from JWT token
    return 'BRK001'; // Placeholder, replace with actual implementation
  }
}
