package com.swapnil.service;

import java.util.List;

import com.swapnil.DTO.LandLordDTO;
import com.swapnil.DTO.PropertyDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.model.LandLord;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;

public interface LandlordService {

	public LandLord registerLandLord(LandLordDTO landLord) throws LandLordException;

	public LandLord updateLandLord(LandLord landLord) throws LandLordException;

	public Property addProperty(PropertyDTO property) throws PropertyException, LandLordException;

	public Tenant viewTenant(Integer tenantId) throws TenantException;

	public List<Tenant> viewAllTenant() throws TenantException, LandLordException;
}
