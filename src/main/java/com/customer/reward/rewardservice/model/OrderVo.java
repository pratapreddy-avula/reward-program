package com.customer.reward.rewardservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderVo {	
	private Double orderTotal;
	private String description;
	private Date transactionDate;

}
