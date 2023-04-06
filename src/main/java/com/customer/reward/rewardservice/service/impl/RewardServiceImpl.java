package com.customer.reward.rewardservice.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.reward.rewardservice.model.OrderData;
import com.customer.reward.rewardservice.repo.OrderRepository;
import com.customer.reward.rewardservice.service.RewardService;

@Service
public class RewardServiceImpl implements RewardService{
	
	@Autowired
	OrderRepository orderRepository;
	

	public List<OrderData> getOrderByCustomerAndBetweenDates(Long customerId, Date startDate, Date endDate) {
		
		return orderRepository.findByCustomerRewardPointsBetweenDates(customerId, startDate, endDate);
	}
	
	
	

	
}
