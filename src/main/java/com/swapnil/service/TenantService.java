package com.swapnil.service;

import java.util.List;

import com.swapnil.DTO.TenantDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;

public interface TenantService {

	public String registerTenant(TenantDTO tenat)throws TenantException;
	public String updateTenant(Tenant tenant,String key)throws TenantException,LandLordException,UserSessionException;
	public List<Property> viewProperties(String key)throws PropertyException,UserSessionException;
	public String rentProperty(Integer propertyId,String key)throws PropertyException,TenantException,UserSessionException;
	
}
