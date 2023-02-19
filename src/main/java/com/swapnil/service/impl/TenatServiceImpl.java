package com.swapnil.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.DTO.TenatDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenatException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Property;
import com.swapnil.model.Tenat;
import com.swapnil.repository.CurrentUserSessionDAO;
import com.swapnil.repository.PropertyDAO;
import com.swapnil.repository.TenatDAO;
import com.swapnil.service.TenatService;

@Service
public class TenatServiceImpl implements TenatService{

	@Autowired
	private TenatDAO tenatDao;
	@Autowired
	private PropertyDAO propertyDao;
	@Autowired
	private CurrentUserSessionDAO currentUserDao;
	@Override
	public String registerTenat(TenatDTO tenat) throws TenatException {
		
		Optional<Tenat> teoptional=tenatDao.findByMobileNo(tenat.getMobileNo());
		if(teoptional.isPresent()) {
			throw new TenatException("User already register with this number");
		}
		Tenat ten=new Tenat(tenat.getFirstName(), tenat.getLastName(), tenat.getMobileNo(), tenat.getAdharNo(),tenat.getPassword());
		return tenatDao.save(ten).toString();
	}

	@Override
	public String updateTenat(Tenat tenat,String key) throws TenatException,LandLordException,UserSessionException {
		
		Optional<CurrentUserSession> currentUser=currentUserDao.findByUuId(key);
		if(currentUser.isPresent()) {
		Optional<Tenat> teoptional=tenatDao.findByMobileNo(tenat.getMobileNo());
		if(teoptional.isPresent()) {
			Property pro=tenat.getProperty();
			Optional<Property> prop=propertyDao.findById(pro.getPropertyId());
			if(prop.isPresent()) {
				Tenat ten=teoptional.get();
				Property oldproperty=ten.getProperty();
				oldproperty.setAvailability(true);
				propertyDao.save(oldproperty);
				Property newProperty=prop.get();
				newProperty.setAvailability(false);
				newProperty.setTenat(tenat);
				propertyDao.save(newProperty);
				return tenatDao.save(ten).toString();

			}
			throw new LandLordException("Landlord is not present");
			
		}
		throw new TenatException("User already register with this number");
		}else {
			throw new UserSessionException("login First");
		}
		
			
	}

	@Override
	public List<Property> viewProperties(String key) throws PropertyException ,UserSessionException{
		Optional<CurrentUserSession> currentUser=currentUserDao.findByUuId(key);
		if(currentUser.isPresent()) {
		List<Property> propertyList=propertyDao.findAll();
		List<Property> availabilityList=propertyList.stream().filter(p->p.isAvailability()).toList();
		
		return availabilityList;
		}
		throw new UserSessionException("login First");
	}

	@Override
	public String rentProperty(Integer propertyId,String key) throws PropertyException,TenatException,UserSessionException {
		Optional<CurrentUserSession> currentUser=currentUserDao.findByUuId(key);
		if(currentUser.isPresent()) {
			Optional<Tenat> tenat=tenatDao.findByMobileNo(currentUser.get().getMobileNo());
			if(tenat.isPresent()) {
				Optional<Property> property=propertyDao.findById(propertyId);
				if(property.isPresent()) {
					Tenat te=tenat.get();
					Property oldProperty=te.getProperty();
					oldProperty.setAvailability(true);
					propertyDao.save(oldProperty);
					Property newProperty=property.get();
					newProperty.setAvailability(false);
					newProperty.setTenat(te);
					propertyDao.save(newProperty);
					
					return tenatDao.save(te).toString();
				}else {
					throw new PropertyException("Property not found");
				}
			}else {
				throw new TenatException("User not present");
			}
		}
		
		
		throw new UserSessionException("login First");
	}

}
