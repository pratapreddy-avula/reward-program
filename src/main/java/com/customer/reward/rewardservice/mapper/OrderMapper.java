package com.customer.reward.rewardservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.customer.reward.rewardservice.model.Customer;
import com.customer.reward.rewardservice.model.CustomerOrderVo;
import com.customer.reward.rewardservice.model.CustomerVo;
import com.customer.reward.rewardservice.model.OrderData;
import com.customer.reward.rewardservice.model.OrderVo;

@Component
public class OrderMapper {
	
	public Customer map(CustomerVo customerVo)
	{
		Customer customer=new Customer();
		customer.setId(customerVo.getId());
		customer.setName(customerVo.getName());
		return customer;
	}
	
	public List<OrderData> map(CustomerOrderVo customerOrderVo){
		List<OrderData> orders=new ArrayList<>();
		if(!CollectionUtils.isEmpty(customerOrderVo.getOrders())) {
		for(OrderVo orderVo:customerOrderVo.getOrders()) {
			orders.add(convert(orderVo, customerOrderVo.getCustomerId()));
		}
			
		}
		return orders;
		
	}
	
	private OrderData convert(OrderVo ordderVo, Long customerId) {
		OrderData order=new OrderData();
		order.setCustomerId(customerId);
		order.setOrderTotal(ordderVo.getOrderTotal());
		order.setDescription(ordderVo.getDescription());
		order.setOrderDate(ordderVo.getTransactionDate());
		return order;
	}

}
