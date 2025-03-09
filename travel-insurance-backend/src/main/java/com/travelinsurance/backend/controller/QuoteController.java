package com.travelinsurance.backend.controller;

import com.travelinsurance.backend.entity.Quote;
import com.travelinsurance.backend.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping
    public Quote createQuote(@RequestBody Quote quote) {
        return quoteService.generateQuote(quote);
    }

    @PutMapping("/{quoteId}/status")
    public Quote updateQuoteStatus(
            @PathVariable String quoteId,
            @RequestParam String status) {
        return quoteService.updateQuoteStatus(quoteId, status);
    }

    @GetMapping("/broker/{brokerId}")
    public List<Quote> getQuotesByBroker(@PathVariable Long brokerId) {
        return quoteService.getQuotesByBroker(brokerId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Quote> getQuotesByCustomer(@PathVariable Long customerId) {
        return quoteService.getQuotesByCustomer(customerId);
    }

    @GetMapping("/{quoteId}")
    public Quote getQuoteDetails(@PathVariable String quoteId) {
        return quoteService.getQuoteDetails(quoteId);
    }
}
