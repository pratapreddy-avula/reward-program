package com.customer.reward.rewardservice.service;

import java.text.ParseException;
import java.util.List;

import com.customer.reward.rewardservice.model.Customer;
import com.customer.reward.rewardservice.model.CustomerOrderVo;
import com.customer.reward.rewardservice.model.CustomerVo;
import com.customer.reward.rewardservice.model.OrderData;

public interface CustomerService {
	
	public void save(CustomerVo customer);
	public Customer getCustomerById(Long id);
	public List<OrderData> getCustomerOrders(Long id);	
	public void saveCustomerOrders(CustomerOrderVo customerOrderVo);
	public Double getRewardPoints(Long customerId, String date, int duration)throws ParseException;
	

}
