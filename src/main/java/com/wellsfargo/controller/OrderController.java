package com.wellsfargo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.dto.OrderRequest;
import com.wellsfargo.dto.OrderResponse;
import com.wellsfargo.model.AuthRequest;
import com.wellsfargo.model.Customer;
import com.wellsfargo.service.CustomerService;
import com.wellsfargo.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
	@Autowired
	private CustomerService customerService;
	
    @Autowired
    private JwtUtil jwtUtil;
    
	
	  @Autowired private AuthenticationManager authenticationManager;
	 

    @GetMapping("/")
    public String welcome() {
        return "Welcome to hello world !!";
    }

	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}
	 

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
