package com.example.spring.rest.api.services;

import com.example.spring.rest.api.entities.Address;
import com.example.spring.rest.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    public List<Address> getAll(){
        return addressRepository.findAll();
    }
//    public Address getById(Long id)throws Exception{
//        return addressRepository.findById(id).orElseThrow(() -> new Exception("RECORD_NOT_FOUND"));
//    }
    public Address getById(Long id) throws Exception {
        return addressRepository.findById(id).orElseThrow(() -> new Exception("RECORD_NOT_FOUND"));
    }

}
