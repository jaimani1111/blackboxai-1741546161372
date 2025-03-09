import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class QuoteService {
  private readonly API_URL = 'http://localhost:8080/api/quotes';

  constructor(private http: HttpClient, private authService: AuthService) {}

  createQuote(quote: any): Observable<any> {
    return this.http.post(this.API_URL, quote);
  }

  updateQuoteStatus(quoteId: string, status: string): Observable<any> {
    return this.http.put(`${this.API_URL}/${quoteId}/status`, null, {
      params: { status }
    });
  }

  getQuotesByBroker(brokerId: string): Observable<any> {
    return this.http.get(`${this.API_URL}/broker/${brokerId}`);
  }

  getQuotesByCustomer(customerId: string): Observable<any> {
    return this.http.get(`${this.API_URL}/customer/${customerId}`);
  }

  getQuoteDetails(quoteId: string): Observable<any> {
    return this.http.get(`${this.API_URL}/${quoteId}`);
  }
}
