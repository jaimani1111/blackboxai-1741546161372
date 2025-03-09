package com.travelinsurance.backend.repository;

import com.travelinsurance.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByPhone(String phone);
}
