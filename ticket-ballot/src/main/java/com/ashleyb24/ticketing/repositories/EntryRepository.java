package com.ashleyb24.ticketing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashleyb24.ticketing.models.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Integer>{

}
