package com.swapnil.service;

import java.util.List;

import com.swapnil.DTO.LandLordDTO;
import com.swapnil.DTO.PropertyDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.LandLord;
import com.swapnil.model.Tenant;

public interface LandLordService {

	public String registerLandLord(LandLordDTO landLord)throws LandLordException;
	public String updateLandLord(LandLord landLord,String key)throws LandLordException,UserSessionException;
	public String addProperty(PropertyDTO property,String key)throws PropertyException,UserSessionException,LandLordException;
	public Tenant viewTenant(Integer tenantId,String key)throws TenantException,UserSessionException;
	public List<Tenant> viewAllTenant(String key)throws TenantException,LandLordException,UserSessionException;
	
}
