package com.customer.reward.rewardservice.model;

import java.util.List;

import lombok.Data;

@Data
public class CustomerOrderVo {

	Long customerId;
	List<OrderVo> orders;
}
