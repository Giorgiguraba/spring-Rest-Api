package com.example.spring.rest.api.services;

import com.example.spring.rest.api.dto.AddCustomer;
import com.example.spring.rest.api.dto.SearchCustomer;
import com.example.spring.rest.api.dto.request.Paging;
import com.example.spring.rest.api.entities.Address;
import com.example.spring.rest.api.entities.Customer;
import com.example.spring.rest.api.repositories.CustomerRepository;
import com.example.spring.rest.api.utils.GeneratUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressService addressService;
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
    public Customer getCustomerById(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("RECORD_NOT_FOUND"));
    }
    @Transactional
    public Customer add(AddCustomer addCustomer) throws Exception{
        Customer customer = new Customer();
        customer.setCreateDate(new Date());
        customer.setFirstName(addCustomer.getFirstName());
        customer.setLastName(addCustomer.getLastName());
        customer.setMiddleName(addCustomer.getMiddleName());
//        GeneratUtil.getCopyOf(addCustomer, customer); #დასაფიქსი მაქვს ერორი
//
//        Address address = addressService.getById(addCustomer.getAddressId());
//
//        customer.setAddress(address);
        return customerRepository.save(customer);
    }
    @Transactional
    public Customer edit(Long id, AddCustomer addCustomer) throws Exception{
        Customer customer = getCustomerById(id);
        GeneratUtil.getCopyOf(addCustomer, customer);
        if(addCustomer.getAddressId() != null && !addCustomer.getAddressId().equals(customer.getAddress().getAddressId())){
            Address address = addressService.getById(addCustomer.getAddressId());
            customer.setAddress(address);
        }
        return customerRepository.save(customer);
    }
    public Slice<Customer> search(SearchCustomer searchCustomer, Paging paging){
        String name = null;
        if (searchCustomer.getName() != null && searchCustomer.getName().equals("")){
            name = "%" +searchCustomer.getName() + "%";
        }
        Pageable pageable = PageRequest.of(paging.getPage(), paging.getSize(), Sort.by("create_data").descending());
        return customerRepository.search(searchCustomer.getActive(), name, pageable);
    }
}
