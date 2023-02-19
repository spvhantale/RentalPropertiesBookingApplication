package com.swapnil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.LandLord;

@Repository
public interface LandLordDAO extends JpaRepository<LandLord, Integer>{

	public Optional<LandLord> findByMobileNo(String mobileNo);
}
