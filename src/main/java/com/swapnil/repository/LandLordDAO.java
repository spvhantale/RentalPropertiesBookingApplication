package com.swapnil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swapnil.model.LandLord;
import com.swapnil.model.Tenant;

@Repository
public interface LandLordDAO extends JpaRepository<LandLord, Integer>{

	public Optional<LandLord> findByMobileNumber(String mobileNumber);
	
	@Query("Select l.tenants from LandLord l where l.mobileNumber=?1")
	public List<Tenant> getAllTenant(String mobileNumber);
}
