package com.wellsfargo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.dto.OrderRequest;
import com.wellsfargo.dto.OrderResponse;
import com.wellsfargo.model.Customer;
import com.wellsfargo.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer saveOrder(OrderRequest request) {
		return customerRepository.save(request.getCustomer());
}
	
	public List<Customer> getAllOrders(){
		return customerRepository.findAll();
	}
	
	public List<OrderResponse> getJoinInfo() {
		return customerRepository.getJoinInformation();
	}
}