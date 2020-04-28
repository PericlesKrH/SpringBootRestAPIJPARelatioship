/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perilee.controllers;

import com.perilee.entities.Customer;
import com.perilee.entities.Orders;
import com.perilee.repositories.CustomerRepository;
import com.perilee.repositories.OrdersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrdersController {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private OrdersRepository ordersRepo;

    @GetMapping("/orders")
    public List<Orders> getOrders() {
        return ordersRepo.findAll();
    }

    @PostMapping("/customer/{customerId}/orders")
    public Orders createOrderByCustomerId(@PathVariable(value = "customerId") Integer id,
            @RequestBody Orders order) throws Exception {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new Exception("customer not found:" + id));
        order.setCustomer(customer);
        return ordersRepo.save(order);
    }

    //Paizei me JSON se morfi {"name":"smartphone","customer":{"name":"Kevin"}}
    @PostMapping("/orders")
    public Orders createOrder(@RequestBody Orders order) {

        return ordersRepo.save(order);
    }
    
   // @PutMapping("/customers/{customerId}/orders/{orderId}")
   // @DeleteMapping("/customers/{customerId}/orders/{orderId}")
}
