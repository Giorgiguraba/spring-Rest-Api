package com.example.spring.rest.api.controller;

import com.example.spring.rest.api.dto.AddCustomer;
import com.example.spring.rest.api.dto.SearchCustomer;
import com.example.spring.rest.api.dto.request.RequestData;
import com.example.spring.rest.api.entities.Customer;
import com.example.spring.rest.api.services.CustomerService;
import com.example.spring.rest.api.utils.GeneratUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@PreAuthorize("hasRole('ADMIN')")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PreAuthorize("hasAuthority('customer:read')")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> getAll() {
        return customerService.getAllCustomer();
    }
    @PreAuthorize("hasAuthority('customer:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Customer getCustomerById(@PathVariable Long id) throws Exception {
//        return customerService.getCustomerById(id);
        var x = customerService.getCustomerById(id);
        return x;
    }

    @PreAuthorize("hasAuthority('customer:add')")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Customer add(@RequestBody AddCustomer addCustomer) throws Exception {
        GeneratUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "addressId"));
        return customerService.add(addCustomer);
    }
    @PreAuthorize("hasAuthority('customer:add')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Customer edit(@PathVariable Long id, @RequestBody AddCustomer addCustomer) throws Exception {
        GeneratUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "addressId"));
        return customerService.edit(id, addCustomer);
    }
    @PreAuthorize("hasAuthority('customer:read')")
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    public Slice<Customer> search(@RequestBody RequestData<SearchCustomer> rd){
        return customerService.search( rd.getData(), rd.getPaging());
    }
}

