package com.ashleyb24.ticketing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashleyb24.ticketing.models.Customer;
import com.ashleyb24.ticketing.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> listCustomers(){
		return customerRepository.findAll();
	}
	
	/*
	 * Returns the customer for the given id. If there is no customer for given id
	 * then IllegalArgumentException is thrown.
	 */
	public Customer getCustomerById(int id){
		return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer id: " + id));
	}
	
	public Customer saveCustomer(Customer cust){
		return customerRepository.save(cust);
	}
	
	public void deleteCustomer(int id){
		customerRepository.deleteById(id); //this throws IllegalArgumentException if there is no customer for id
	}

}
