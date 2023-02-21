package com.swapnil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Tenant;

@Repository
public interface TenantDAO extends JpaRepository<Tenant, Integer>{

	public Optional<Tenant> findByMobileNo(String mobileNo);
}
