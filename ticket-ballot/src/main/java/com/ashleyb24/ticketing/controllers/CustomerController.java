package com.ashleyb24.ticketing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashleyb24.ticketing.models.Customer;
import com.ashleyb24.ticketing.services.CustomerService;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public Customer customerById(@RequestParam(value="id") int id){
		return customerService.getCustomerById(id);
	}
	
	@RequestMapping(value = "/customer/all", method = RequestMethod.GET)
	public List<Customer> listCustomers(){
		return customerService.listCustomers();
	}
	
	@DeleteMapping(value = "/customer")
	public void deleteCustomer(@RequestParam(value="id") int id){
		customerService.deleteCustomer(id);
	}
}
