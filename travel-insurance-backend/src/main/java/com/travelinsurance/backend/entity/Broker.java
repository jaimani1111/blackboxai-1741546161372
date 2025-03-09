package com.travelinsurance.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Broker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    private String password;
    private String brokerId;
}
