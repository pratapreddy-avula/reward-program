package com.customer.reward.rewardservice.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer.reward.rewardservice.model.OrderData;
@Repository
public interface OrderRepository  extends JpaRepository<OrderData, Long> {
	
	public List<OrderData> findByCustomerId(Long customerId);
	
	@Query("from OrderData o where customerId=:customerId and rewardPoints > 0 and orderDate >=:start and orderDate <:end")
	public List<OrderData> findByCustomerRewardPointsBetweenDates(Long customerId, Date start,Date end);
		
}
