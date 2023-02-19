package com.swapnil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Tenat;

@Repository
public interface TenatDAO extends JpaRepository<Tenat, Integer>{

	public Optional<Tenat> findByMobileNo(String mobileNo);
}
