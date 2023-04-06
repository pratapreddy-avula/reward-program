package com.customer.reward.rewardservice.service;

import java.util.Date;
import java.util.List;

import com.customer.reward.rewardservice.model.OrderData;

public interface RewardService {
	
	public List<OrderData> getOrderByCustomerAndBetweenDates(Long customerId, Date startDate, Date endDate);
	
}
