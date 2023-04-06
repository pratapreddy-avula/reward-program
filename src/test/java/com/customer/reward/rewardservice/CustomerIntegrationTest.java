package com.customer.reward.rewardservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.customer.reward.rewardservice.model.Customer;
import com.customer.reward.rewardservice.model.CustomerOrderVo;
import com.customer.reward.rewardservice.model.CustomerVo;
import com.customer.reward.rewardservice.model.OrderVo;
import com.customer.reward.rewardservice.repo.CustomerRepository;
import com.customer.reward.rewardservice.repo.OrderRepository;
import com.customer.reward.rewardservice.service.CustomerService;
import com.customer.reward.rewardservice.utils.DateTimeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;	
	
	@Test
	public void testCustomerSave()throws Exception {
		CustomerVo customer=new CustomerVo();
		customer.setName("Pratap");
		mockMvc.perform(post("/customer/save").contentType("application/json").content(objectMapper.writeValueAsString(customer)))
		.andExpect(status().isOk());
		
		Optional<Customer> cus=customerRepository.findById(1l);
		assertEquals("Pratap", cus.get().getName());
		
	}
	@Test
	public void testGetRewardPointsForMonth()throws Exception {
	
		CustomerOrderVo customerOrderVo=new CustomerOrderVo();
		customerOrderVo.setCustomerId(1L);
		customerOrderVo.setOrders(buildOrder(20));
		mockMvc.perform(post("/customer/orders").contentType("application/json").content(objectMapper.writeValueAsString(customerOrderVo)))
		.andExpect(status().isOk());
		
		Double rewardPoints=customerService.getRewardPoints(1l, getCurrentDate(), 30);
		assertNotNull(rewardPoints);
		assertEquals(Double.valueOf(960), rewardPoints);
	}
	
	private List<OrderVo> buildOrder(int noOfDays){
		List<OrderVo>  orders=new ArrayList<>();
		for(int i=0;i<noOfDays;i++) {
			OrderVo orderVo=new OrderVo(Double.valueOf(i+95), "Test Order"+i, DateTimeUtils.addDays(new Date(), i, null));
			orders.add(orderVo);
		}
		
		return orders;
	}
	
	private String getCurrentDate() {
		LocalDate date= LocalDate.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return formatter.format(date);
	}
	
	

}
