package com.example.spring.rest.api.services;

import com.example.spring.rest.api.entities.Customer;
import com.example.spring.rest.api.repository.CustomerRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
    public Customer getCustomerById(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("RECORD_NOT_FOUND"));
    }
}
