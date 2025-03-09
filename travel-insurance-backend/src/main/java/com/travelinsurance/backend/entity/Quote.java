package com.travelinsurance.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String quoteId;
    private Date startDate;
    private Date endDate;
    private String destinationCity;
    private int numberOfTravelers;
    private double premiumAmount;
    private String status; // PENDING, APPROVED, REJECTED
    
    @ManyToOne
    @JoinColumn(name = "broker_id")
    private Broker broker;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ElementCollection
    private List<String> activities; // e.g., Scuba Diving, Skydiving
}
