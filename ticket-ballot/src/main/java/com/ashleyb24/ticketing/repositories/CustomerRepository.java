package com.ashleyb24.ticketing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashleyb24.ticketing.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
}
