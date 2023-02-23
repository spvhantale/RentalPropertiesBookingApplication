package com.swapnil.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swapnil.DTO.LandLordDTO;
import com.swapnil.DTO.PropertyDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.LandLord;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;
import com.swapnil.repository.CurrentUserSessionDAO;
import com.swapnil.repository.LandLordDAO;
import com.swapnil.repository.PropertyDAO;
import com.swapnil.repository.TenantDAO;
import com.swapnil.service.LandService;

@Service
public class LandServiceImpl implements LandService{


	private LandLordDAO landLordDao;
	private PropertyDAO propertyDao;
	private TenantDAO tenatDao;
	private CurrentUserSessionDAO userSessionDao;
	
	
	public LandServiceImpl(LandLordDAO landLordDao,PropertyDAO propertyDao,TenantDAO tenatDao,CurrentUserSessionDAO userSessionDao) {
		// TODO Auto-generated constructor stub
		this.landLordDao=landLordDao;
		this.propertyDao=propertyDao;
		this.tenatDao=tenatDao;
		this.userSessionDao=userSessionDao;
	}
	
	@Override
	public String registerLandLord(LandLordDTO landLord) throws LandLordException {
		Optional<LandLord> land=landLordDao.findByMobileNo(landLord.getMobileNo());
		if(land.isPresent()) {
			throw new LandLordException("Already register with this number");
		}
		LandLord landLo=new LandLord(landLord.getFirstName(), landLord.getLastName(), landLord.getMobileNo(), landLord.getPassword(), landLord.getAdharNo());
		return landLordDao.save(landLo).toString();
	}

	@Override
	public String updateLandLord(LandLord landLord, String key) throws LandLordException,UserSessionException {
		
		Optional<CurrentUserSession> current=userSessionDao.findByUuId(key);
		if(current.isPresent()) {
			if(landLord.getMobileNo().equals(current.get().getMobileNo())) {
				List<Tenant> tenatList=landLord.getTenants();
				for(Tenant te:tenatList) {
					tenatDao.save(te);
				}
				List<Property> propertyList=landLord.getProperties();
				for(Property pe:propertyList) {
					propertyDao.save(pe);
				}
				return landLordDao.save(landLord).toString();
				
			}else {
				throw new LandLordException("Login with correct key");
			}
		}else {
			throw new UserSessionException("Login first");
		}
		
	}

	@Override
	public String addProperty(PropertyDTO property, String key) throws PropertyException,LandLordException,UserSessionException {
		
		Optional<CurrentUserSession> current=userSessionDao.findByUuId(key);
		if(current.isPresent()) {
			Optional<LandLord> land=landLordDao.findByMobileNo(current.get().getMobileNo());
			if(land.isPresent()) {
				
				 LandLord landLa=land.get();
				Property prop=new Property(property.getCity(), property.getStreetNo(),property.getState(), property.getBedRoom(), property.getHall(), property.getRent(), true);
				Property pro=propertyDao.save(prop);
				landLa.getProperties().add(pro);
				return landLordDao.save(landLa).toString();
				
				
			}else {
				throw new LandLordException("not present");
			}
		}
		throw new UserSessionException("Login first");
		
		
	}

	@Override
	public Tenant viewTenant(Integer tenantId, String key) throws TenantException ,UserSessionException{
		
		Optional<CurrentUserSession> current=userSessionDao.findByUuId(key);
		if(current.isPresent()) {
			Optional<LandLord> land=landLordDao.findByMobileNo(current.get().getMobileNo());
			if(land.isPresent()) {
				List<Tenant> tenatList=land.get().getTenants();
				for(Tenant te:tenatList) {
					if(te.getTenatId()==tenantId) {
						return te;
					}
				}
				throw new TenantException("Tenat not found");
			}
			throw new UserSessionException("user not found");
		}
		
		throw new UserSessionException("Login first");

	}

	@Override
	public List<Tenant> viewAllTenant(String key) throws TenantException, LandLordException,UserSessionException {
		Optional<CurrentUserSession> current=userSessionDao.findByUuId(key);
		if(current.isPresent()) {
			Optional<LandLord> land=landLordDao.findByMobileNo(current.get().getMobileNo());
			if(land.isPresent()) {
				List<Tenant> tenatList=land.get().getTenants();
				return tenatList;
			}else {
				throw new TenantException("Tenat not found");
			}
		}else {
			throw new UserSessionException("Login first");
		}
	}

}
