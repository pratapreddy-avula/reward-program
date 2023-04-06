package com.customer.reward.rewardservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Entity
@Table(name="ORDER_DATA")
@Data
public class OrderData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double orderTotal;
	private Long customerId;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	private Double rewardPoints;

	

}
