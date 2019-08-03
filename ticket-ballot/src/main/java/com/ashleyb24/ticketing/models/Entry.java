package com.ashleyb24.ticketing.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ENTRIES")
public class Entry {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@JsonIgnoreProperties("entries")
	@ManyToOne
	@JoinColumn(name = "EVENT_ID")
	private TicketEvent event;
	
	@JsonIgnoreProperties("entries")
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	
	public Entry(){}
	
	public Entry(TicketEvent event, Customer customer){
		this.event = event;
		this.customer = customer;
	}

	public TicketEvent getEvent() {
		return event;
	}

	public void setEvent(TicketEvent event) {
		this.event = event;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String toString(){
		return "Customer: " + getCustomer() + "\nEvent: " + getEvent(); 
	}
	
	//TODO: Check if customer has already applied for event

}

