package com.travelinsurance.backend.security;

import com.travelinsurance.backend.entity.Broker;
import com.travelinsurance.backend.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BrokerRepository brokerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Broker broker = brokerRepository.findByEmail(email);
        if (broker == null) {
            throw new UsernameNotFoundException("Broker not found with email: " + email);
        }
        return new User(broker.getEmail(), broker.getPassword(), new ArrayList<>());
    }
}
