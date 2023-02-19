package com.swapnil.service;

import java.util.List;

import com.swapnil.DTO.LandLordDTO;
import com.swapnil.DTO.PropertyDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenatException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.LandLord;
import com.swapnil.model.Tenat;

public interface LandLordService {

	public String registerLandLord(LandLordDTO landLord)throws LandLordException;
	public String updateLandLord(LandLord landLord,String key)throws LandLordException,UserSessionException;
	public String addProperty(PropertyDTO property,String key)throws PropertyException,UserSessionException,LandLordException;
	public Tenat viewTenat(Integer tenatId,String key)throws TenatException,UserSessionException;
	public List<Tenat> viewAllTenat(String key)throws TenatException,LandLordException,UserSessionException;
	
}
