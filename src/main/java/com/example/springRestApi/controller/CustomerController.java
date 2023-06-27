package com.example.springRestApi.controller;

import com.example.springRestApi.entities.Customer;
import com.example.springRestApi.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    private CustomerService customerService;
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> getAll(){
        return customerService.getAllCustomer();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Customer getCustomerById(@PathVariable Long id) throws Exception{
//        var x = customerService.getCustomerById(id);
//        return x;
        return customerService.getCustomerById(id);
    }


}
