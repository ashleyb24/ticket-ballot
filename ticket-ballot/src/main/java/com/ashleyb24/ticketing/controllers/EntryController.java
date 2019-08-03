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

import com.ashleyb24.ticketing.models.Entry;
import com.ashleyb24.ticketing.services.EntryService;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class EntryController {
	
	@Autowired
	private EntryService entryService;
	
	@RequestMapping(value = "/entry/all", method = RequestMethod.GET)
	public List<Entry> listEntries(){
		return entryService.listEntries();
	}
	
	@RequestMapping(value = "/entry", method = RequestMethod.GET)
	public Entry entryById(@RequestParam(value="id") int id){
		return entryService.getEntryById(id);
	}
	
	@PostMapping("/entry/new")
	public ResponseEntity<Void> createEntry(@RequestBody Entry entry){
		Entry createdEntry = entryService.saveEntry(entry);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEntry.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/entry")
	public ResponseEntity<Entry> updateEntry(@RequestParam(value="id") int id, @RequestBody Entry entry){
		Entry updatedEntry = entryService.saveEntry(entry);
		
		return new ResponseEntity<Entry>(updatedEntry, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/entry")
	public void deleteEntryById(@RequestParam(value="id") int id){
		entryService.deleteEntry(id);
	}

}
