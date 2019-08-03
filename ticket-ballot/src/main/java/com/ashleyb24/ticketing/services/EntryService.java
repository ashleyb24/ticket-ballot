package com.ashleyb24.ticketing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashleyb24.ticketing.models.Entry;
import com.ashleyb24.ticketing.repositories.EntryRepository;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	public List<Entry> listEntries(){
		return entryRepository.findAll();
	}
	
	public Entry getEntryById(int id) throws IllegalArgumentException {
		return entryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Entry Id: " + id));
	}
	
	public Entry saveEntry(Entry entry){
		return entryRepository.save(entry);
	}
	
	public void deleteEntry(int id){
		entryRepository.deleteById(id);
	}
	

}
