package com.travelinsurance.backend.service;

import com.travelinsurance.backend.entity.Quote;
import java.util.List;

public interface QuoteService {
    Quote generateQuote(Quote quote);
    Quote updateQuoteStatus(String quoteId, String status);
    List<Quote> getQuotesByBroker(Long brokerId);
    List<Quote> getQuotesByCustomer(Long customerId);
    Quote getQuoteDetails(String quoteId);
    double calculatePremium(Quote quote);
}
