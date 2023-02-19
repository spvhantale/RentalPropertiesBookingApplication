package com.swapnil.service;

import com.swapnil.DTO.TenatDTO;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenatException;
import com.swapnil.model.Tenat;

public interface TenatService {

	public String registerTenat(TenatDTO tenat)throws TenatException;
	public String updateTenat(Tenat tenat)throws TenatException;
	public String viewProperties()throws PropertyException;
	public String rentProperty(Integer propertyId)throws PropertyException;
	
}
