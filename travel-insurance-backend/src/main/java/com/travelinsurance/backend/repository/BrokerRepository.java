package com.travelinsurance.backend.repository;

import com.travelinsurance.backend.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepository extends JpaRepository<Broker, Long> {
    Broker findByEmail(String email);
    Broker findByBrokerId(String brokerId);
}
