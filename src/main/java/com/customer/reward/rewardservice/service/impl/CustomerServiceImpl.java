package com.customer.reward.rewardservice.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.customer.reward.rewardservice.mapper.OrderMapper;
import com.customer.reward.rewardservice.model.Customer;
import com.customer.reward.rewardservice.model.CustomerOrderVo;
import com.customer.reward.rewardservice.model.CustomerVo;
import com.customer.reward.rewardservice.model.OrderData;
import com.customer.reward.rewardservice.repo.CustomerRepository;
import com.customer.reward.rewardservice.repo.OrderRepository;
import com.customer.reward.rewardservice.service.CustomerService;
import com.customer.reward.rewardservice.utils.DateTimeUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	
	@Value("${over.amount:100}")
	private int overAmount;
	@Value("${over.points:2}")
	private int overPoints;

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderMapper orderMapper;


	@Override
	@Transactional
	public void save(CustomerVo customer) {
		customerRepository.save(orderMapper.map(customer));
	}

	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			return customer.get();
		} else {
			log.info("No customer details found with this Id {}", id);
			return null;
		}
	}
	
	public void saveCustomerOrders(CustomerOrderVo customerOrderVo) {
		List<OrderData> orders=orderMapper.map(customerOrderVo);
		if(!CollectionUtils.isEmpty(orders)) {
		for (OrderData order : orders) {
			if(order.getOrderTotal()!=null) {
				order.setRewardPoints(calculateRewardPoints(order));
			}
		}
		orderRepository.saveAll(orders);
		log.info("Orders saved successfully");
		}		
	}

	@Override
	public List<OrderData> getCustomerOrders(Long id) {
		return orderRepository.findByCustomerId(id);
	}
	
	@Transactional
	public void saveOrders(OrderData order) {
		if(order.getOrderTotal()!=null) {
			order.setRewardPoints(calculateRewardPoints(order));			
		}
		orderRepository.save(order);
	}
	
	private Double calculateRewardPoints(OrderData order) {
		if(order.getOrderTotal()>=overAmount) {
			return (order.getOrderTotal()-overAmount)*overPoints+ 1*50;
		}
		return null;
	}
	
	public Double getRewardPoints(Long customerId, String date, int duration)throws ParseException {
		Date validDate=null;
		try
		{
			validDate=DateTimeUtils.parseDate(date);		
		}catch (ParseException e) {
			log.error("Send the Invalid format Date ,Skip the porcess {}",date);
			throw e;
		}
		
		
	Date startDate=	DateTimeUtils.roundDownToStartOfDay(validDate);
	Date endDate=DateTimeUtils.addDays(validDate,duration+1,null);
	
	List<OrderData> orders=orderRepository.findByCustomerRewardPointsBetweenDates(customerId, startDate, endDate);
	
	return orders.stream().mapToDouble(OrderData::getRewardPoints).sum();
			
	}
	
	
	
	

}
