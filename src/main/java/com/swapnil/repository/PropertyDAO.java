package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Property;

@Repository
public interface PropertyDAO extends JpaRepository<Property, Integer>{

	
}
