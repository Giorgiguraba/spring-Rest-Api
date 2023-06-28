package com.example.spring.rest.api.controller;

import com.example.spring.rest.api.dto.AddCustomer;
import com.example.spring.rest.api.entities.Customer;
import com.example.spring.rest.api.services.CustomerService;
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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Customer edit(@PathVariable Long id, @RequestBody AddCustomer addCustomer) throws Exception{

    }

}
