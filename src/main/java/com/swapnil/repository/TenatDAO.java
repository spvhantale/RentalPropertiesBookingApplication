package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Tenat;

@Repository
public interface TenatDAO extends JpaRepository<Tenat, Integer>{

}
