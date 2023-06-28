package com.example.spring.rest.api.controller;

import com.example.spring.rest.api.dto.AddCustomer;
import com.example.spring.rest.api.entities.Customer;
import com.example.spring.rest.api.services.CustomerService;
import com.example.spring.rest.api.utils.GeneratUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
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
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Customer add(@RequestBody AddCustomer addCustomer) throws Exception{
        GeneratUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "addressId"));
        return customerService.add(addCustomer);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Customer edit(@PathVariable Long id, @RequestBody AddCustomer addCustomer) throws Exception{
        GeneratUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "addressId"));
        return customerService.edit(id, addCustomer);
    }

}
