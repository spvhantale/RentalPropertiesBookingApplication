package com.swapnil.service;

import java.util.List;

import com.swapnil.DTO.TenantDTO;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;

public interface TenantService {

	public Tenant registerTenant(TenantDTO tenant) throws TenantException;

	public Tenant updateTenant(Tenant tenant) throws TenantException;

	public List<Property> viewProperties() throws TenantException, PropertyException;

	public String rentProperty(Integer propertyId) throws PropertyException, TenantException;
}
