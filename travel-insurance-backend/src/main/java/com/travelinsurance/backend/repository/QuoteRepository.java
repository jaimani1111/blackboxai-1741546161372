package com.travelinsurance.backend.repository;

import com.travelinsurance.backend.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByBrokerId(Long brokerId);
    List<Quote> findByCustomerId(Long customerId);
    Quote findByQuoteId(String quoteId);
}
