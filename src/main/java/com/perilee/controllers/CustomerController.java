/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perilee.controllers;

import com.perilee.entities.Customer;
import com.perilee.repositories.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author perry
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepo.save(customer);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Integer id) {
        Customer customer = customerRepo.findById(id).get();
        // ResponseEntity<Customer> entity;
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable(value = "id") Integer id,
            @RequestBody Customer customerDetails
    ) throws Exception {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new Exception("customer not found:" + id));
        customer.setName(customerDetails.getName());
        Customer updatedCustomer = customerRepo.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Integer id) throws Exception {
        //uses JavaLambdas
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new Exception("customer not found:" + id));
        customerRepo.delete(customer);
        String deletedMessage = "Customer deleted successfully";
        return deletedMessage;
    }
}
