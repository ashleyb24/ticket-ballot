package com.ashleyb24.ticketing.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ashleyb24.ticketing.models.TicketEvent;
import com.ashleyb24.ticketing.services.TicketEventService;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class TicketEventController {

	@Autowired
	private TicketEventService tes;

	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public TicketEvent ticketEventById(@RequestParam(value="id") int id){
		return tes.getEventById(id);
	}

	@RequestMapping(value = "/event/all", method = RequestMethod.GET)
	public List<TicketEvent> listEvents(){
		return tes.listTicketEvents();
	}

	@DeleteMapping(value = "/event")
	public void deleteEvent(@RequestParam(value="id") int id){
		tes.deleteEvent(id);
	}

	@PutMapping("/event")
	public ResponseEntity<TicketEvent> updateEvent(@RequestParam(value="id") int id, @RequestBody TicketEvent event){
		TicketEvent updatedEvent = tes.updateEvent(event);

		return new ResponseEntity<TicketEvent>(updatedEvent, HttpStatus.OK);
	}

	@PostMapping("/event/new")
	public ResponseEntity<Void> createEvent(@RequestBody TicketEvent event){
		TicketEvent createdEvent = tes.saveNewEvent(event);	

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEvent.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
