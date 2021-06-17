package com.wellsfargo.repository;

import com.wellsfargo.dto.OrderResponse;
import com.wellsfargo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("SELECT new com.wellsfargo.dto.OrderResponse(c.name , p.productName) FROM Customer c JOIN c.products p")
	public List<OrderResponse> getJoinInformation();
}
  