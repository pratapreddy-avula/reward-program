package com.customer.reward.rewardservice.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.reward.rewardservice.model.Customer;
import com.customer.reward.rewardservice.model.CustomerOrderVo;
import com.customer.reward.rewardservice.model.CustomerVo;
import com.customer.reward.rewardservice.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(value="/save",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveCustomer(@RequestBody CustomerVo customerVo){
		customerService.save(customerVo);
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
	
	@GetMapping(value="/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId){
		Customer customer=customerService.getCustomerById(customerId);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@PostMapping(value="/orders",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveCustomerOrders(@RequestBody CustomerOrderVo customerOrderVo){
		customerService.saveCustomerOrders(customerOrderVo);
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
	
	@GetMapping(value="/rewards/{customerId}/{startDate}/{duration}")
	public ResponseEntity<? extends Object> getRewardPoints(@PathVariable Long customerId,@PathVariable String startDate,@PathVariable int duration){
		
		try
		{
		Double value=	customerService.getRewardPoints(customerId, startDate, duration);
		return  new ResponseEntity<>(value,HttpStatus.OK);
		}catch (ParseException e) {
			return new ResponseEntity<>("Please pass the valid date format YYYY-MM-DD :"+startDate,HttpStatus.BAD_REQUEST);
		}
		
	}

}
