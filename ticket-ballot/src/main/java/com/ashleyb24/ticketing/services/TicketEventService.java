package com.ashleyb24.ticketing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashleyb24.ticketing.models.TicketEvent;
import com.ashleyb24.ticketing.repositories.TicketEventRepository;

@Service
public class TicketEventService {
	
	@Autowired
	private TicketEventRepository ter;
	
	public List<TicketEvent> listTicketEvents(){
		return ter.findAll();
	}
	
	public TicketEvent getEventById(int id){
		return ter.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid event id: " + id));
	}
	
	public TicketEvent saveNewEvent(TicketEvent event){
		return ter.save(event);
	}
	
	public TicketEvent updateEvent(TicketEvent event){
		return ter.save(event);
	}
	
	public void deleteEvent(int id){
		ter.deleteById(id);
	}

}
