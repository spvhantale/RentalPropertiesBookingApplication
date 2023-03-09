package com.swapnil.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.swapnil.DTO.TenantDTO;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;

public interface TenantService {

	public Tenant registerTenant(TenantDTO tenant) throws TenantException;

	public Tenant updateTenant(Tenant tenant,Authentication auth) throws TenantException;

	public List<Property> viewProperties() throws TenantException, PropertyException;

	public String rentProperty(Integer propertyId,Authentication auth) throws PropertyException, TenantException;
}
