package com.customer.reward.rewardservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.reward.rewardservice.model.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long>{
	
	public Optional<Customer> findById(Long id);
	
	public Customer findByName(String name);

}
