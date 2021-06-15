package com.wellsfargo.controller;

import com.wellsfargo.dto.OrderRequest;
import com.wellsfargo.dto.OrderResponse;
import com.wellsfargo.model.Customer;
import com.wellsfargo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
	@Autowired
	private CustomerService customerService;

    @PostMapping("/placeOrder")
    public Customer placeOrder(@RequestBody OrderRequest request){
    	return customerService.saveOrder(request);
    }

    @GetMapping("/findAllOrders")
    public List<Customer> findAllOrders(){
        return customerService.getAllOrders();
    }

    @GetMapping("/getInfo")
    public List<OrderResponse> getJoinInformation(){
    	return customerService.getJoinInfo();
    }
}
