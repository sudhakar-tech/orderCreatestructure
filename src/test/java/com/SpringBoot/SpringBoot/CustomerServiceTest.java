package com.SpringBoot.SpringBoot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.DynamicTest.stream;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wellsfargo.controller.OrderController;
import com.wellsfargo.dto.OrderRequest;
import com.wellsfargo.model.Customer;
import com.wellsfargo.model.Product;
import com.wellsfargo.repository.CustomerRepository;
import com.wellsfargo.service.CustomerService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CustomerService.class,CustomerRepository.class})
@WebMvcTest
class CustomerServiceTest {
	
@Autowired
private CustomerService customerService;

@MockBean
private CustomerRepository customerRepository;

@Test
void saveOrder() throws JsonProcessingException {
		
	 List<Product> products = new ArrayList<Product>();
	 products.add(new Product(100, "LG",5,50000));
	 products.add(new Product(101, "LG 1",7,70000));
	 products.add(new Product(102, "LG 2",9,30000));
	 
	 OrderRequest saveOrderRequest = new OrderRequest();
	 saveOrderRequest.setCustomer(new Customer(2, "sudhakar", "sudha@gmail.com", "male", products));
	 when(customerRepository.save(saveOrderRequest.getCustomer())).thenReturn(saveOrderRequest.getCustomer());
     Customer orderRequest = customerService.saveOrder(saveOrderRequest);
     assertNotNull(orderRequest);

}

@Test
void getAllOrdersTest() {
	 List<Product> products = new ArrayList<Product>();
	 products.add(new Product(100, "LG",5,50000));
	 products.add(new Product(101, "LG 1",7,70000));
	 products.add(new Product(102, "LG 2",9,30000));
	 
	 List<Customer> customers = new ArrayList<Customer>();
	 customers.add(new Customer(2,"sudhakar","sudha@gmail.com","male",products));
	 when(customerRepository.findAll()).thenReturn(customers);
	
	 List<Customer> customers2 = customerService.getAllOrders();
	 assertEquals(1,customers2.size());
	 verify(customerRepository,times(1)).findAll();
	
}

}
