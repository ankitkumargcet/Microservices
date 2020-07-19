package com.aki.rentcloud.profile.service;

import com.aki.rentcloud.commons.model.Customer;
import com.aki.rentcloud.profile.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
