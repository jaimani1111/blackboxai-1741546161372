package com.travelinsurance.backend.service.impl;

import com.travelinsurance.backend.entity.Quote;
import com.travelinsurance.backend.repository.QuoteRepository;
import com.travelinsurance.backend.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public Quote generateQuote(Quote quote) {
        // Generate unique quote ID
        quote.setQuoteId("QT-" + UUID.randomUUID().toString().substring(0, 8));
        quote.setStatus("PENDING");
        quote.setPremiumAmount(calculatePremium(quote));
        return quoteRepository.save(quote);
    }

    @Override
    public Quote updateQuoteStatus(String quoteId, String status) {
        Quote quote = quoteRepository.findByQuoteId(quoteId);
        if (quote != null) {
            quote.setStatus(status);
            return quoteRepository.save(quote);
        }
        return null;
    }

    @Override
    public List<Quote> getQuotesByBroker(Long brokerId) {
        return quoteRepository.findByBrokerId(brokerId);
    }

    @Override
    public List<Quote> getQuotesByCustomer(Long customerId) {
        return quoteRepository.findByCustomerId(customerId);
    }

    @Override
    public Quote getQuoteDetails(String quoteId) {
        return quoteRepository.findByQuoteId(quoteId);
    }

    @Override
    public double calculatePremium(Quote quote) {
        double basePremium = 500; // Base premium for Zone 1
        if (quote.getDestinationCity().equals("Ladakh") || 
            quote.getDestinationCity().equals("Srinagar")) {
            basePremium = 1000; // Zone 3
        } else if (quote.getDestinationCity().equals("Goa") || 
                   quote.getDestinationCity().equals("Manali")) {
            basePremium = 700; // Zone 2
        }

        // Age factor
        if (quote.getCustomer().getAge() > 50) {
            basePremium *= 1.2; // 20% surcharge
        }

        // Activities
        if (quote.getActivities().contains("Scuba Diving")) {
            basePremium *= 1.2; // 20% surcharge
        }
        if (quote.getActivities().contains("Skydiving")) {
            basePremium *= 1.3; // 30% surcharge
        }

        // Number of travelers
        basePremium *= quote.getNumberOfTravelers();

        return basePremium;
    }
}
