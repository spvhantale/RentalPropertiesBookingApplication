package com.swapnil.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.DTO.TenantDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;
import com.swapnil.repository.CurrentUserSessionDAO;
import com.swapnil.repository.PropertyDAO;
import com.swapnil.repository.TenantDAO;
import com.swapnil.service.TenantService;

@Service
public class TenantServiceImpl implements TenantService{

	@Autowired
	private TenantDAO tenatDao;
	@Autowired
	private PropertyDAO propertyDao;
	@Autowired
	private CurrentUserSessionDAO currentUserDao;
	@Override
	public String registerTenant(TenantDTO tenat) throws TenantException {
		
		Optional<Tenant> teoptional=tenatDao.findByMobileNo(tenat.getMobileNo());
		if(teoptional.isPresent()) {
			throw new TenantException("User already register with this number");
		}
		Tenant ten=new Tenant(tenat.getFirstName(), tenat.getLastName(), tenat.getMobileNo(), tenat.getAdharNo(),tenat.getPassword());
		return tenatDao.save(ten).toString();
	}

	@Override
	public String updateTenant(Tenant tenant,String key) throws TenantException,LandLordException,UserSessionException {
		
		Optional<CurrentUserSession> currentUser=currentUserDao.findByUuId(key);
		if(currentUser.isPresent()) {
		Optional<Tenant> teoptional=tenatDao.findByMobileNo(tenant.getMobileNo());
		if(teoptional.isPresent()) {
			Property pro=tenant.getProperty();
			Optional<Property> prop=propertyDao.findById(pro.getPropertyId());
			if(prop.isPresent()) {
				Tenant ten=teoptional.get();
				Property oldproperty=ten.getProperty();
				oldproperty.setAvailability(true);
				propertyDao.save(oldproperty);
				Property newProperty=prop.get();
				newProperty.setAvailability(false);
				newProperty.setTenant(tenant);
				propertyDao.save(newProperty);
				return tenatDao.save(ten).toString();

			}
			throw new LandLordException("Landlord is not present");
			
		}
		throw new TenantException("User already register with this number");
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
	public String rentProperty(Integer propertyId,String key) throws PropertyException,TenantException,UserSessionException {
		Optional<CurrentUserSession> currentUser=currentUserDao.findByUuId(key);
		if(currentUser.isPresent()) {
			Optional<Tenant> tenant=tenatDao.findByMobileNo(currentUser.get().getMobileNo());
			if(tenant.isPresent()) {
				Optional<Property> property=propertyDao.findById(propertyId);
				if(property.isPresent()) {
					Tenant te=tenant.get();
					Property oldProperty=te.getProperty();
					oldProperty.setAvailability(true);
					propertyDao.save(oldProperty);
					Property newProperty=property.get();
					newProperty.setAvailability(false);
					newProperty.setTenant(te);
					propertyDao.save(newProperty);
					
					return tenatDao.save(te).toString();
				}else {
					throw new PropertyException("Property not found");
				}
			}else {
				throw new TenantException("User not present");
			}
		}
		
		
		throw new UserSessionException("login First");
	}

}
