package com.swapnil.service;

import com.swapnil.DTO.LandLordDTO;
import com.swapnil.DTO.PropertyDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenatException;
import com.swapnil.model.LandLord;
import com.swapnil.model.Tenat;

public interface LandLordService {

	public String registerLandLord(LandLordDTO landLord)throws LandLordException;
	public String updateLandLord(LandLord landLord)throws LandLordException;
	public String addProperty(PropertyDTO property)throws PropertyException;
	public Tenat viewTenat(Integer tenatId,Integer landLordId)throws TenatException;
	public Tenat viewAllTenat(Integer tenatId)throws TenatException,LandLordException;
	
}
