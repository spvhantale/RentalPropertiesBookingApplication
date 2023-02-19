package com.swapnil.service;

import java.util.List;

import com.swapnil.DTO.TenatDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenatException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.Property;
import com.swapnil.model.Tenat;

public interface TenatService {

	public String registerTenat(TenatDTO tenat)throws TenatException;
	public String updateTenat(Tenat tenat,String key)throws TenatException,LandLordException,UserSessionException;
	public List<Property> viewProperties(String key)throws PropertyException,UserSessionException;
	public String rentProperty(Integer propertyId,String key)throws PropertyException,TenatException,UserSessionException;
	
}
