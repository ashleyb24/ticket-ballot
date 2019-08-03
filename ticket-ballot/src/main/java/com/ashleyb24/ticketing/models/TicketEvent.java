package com.ashleyb24.ticketing.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "EVENTS")
public class TicketEvent {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAME")
	private String eventName;
	
	@Column(name = "VENUE")
	private String venue;
	
	@Column(name = "CAPACITY")
	private int capacity;
	
	@Column(name = "EVENT_TYPE")
	private String eventType;
	
	@JsonIgnoreProperties("event")
	@OneToMany(mappedBy = "event")
	private Set<Entry> entries;
	
	public TicketEvent(){}
	
	public TicketEvent(String eventName, String venue, int capacity, String eventType){
		this.eventName = eventName;
		this.venue = venue;
		this.capacity = capacity;
		this.eventType = eventType;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getEventType(){
		return eventType;
	}
	
	public void setEventType(String eventType){
		this.eventType = eventType;
	}
	
	public String toString(){
		return "Event: " + getEventName() + " @ Venue: " + getVenue();
	}

	public int getId(){
		return id;
	}

}
