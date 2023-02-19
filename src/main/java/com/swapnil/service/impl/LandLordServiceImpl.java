package com.swapnil.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.DTO.LandLordDTO;
import com.swapnil.DTO.PropertyDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenatException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.LandLord;
import com.swapnil.model.Property;
import com.swapnil.model.Tenat;
import com.swapnil.repository.CurrentUserSessionDAO;
import com.swapnil.repository.LandLordDAO;
import com.swapnil.repository.PropertyDAO;
import com.swapnil.repository.TenatDAO;
import com.swapnil.service.LandLordService;

@Service
public class LandLordServiceImpl implements LandLordService{

	@Autowired
	private LandLordDAO landLordDao;
	@Autowired
	private PropertyDAO propertyDao;
	@Autowired
	private TenatDAO tenatDao;
	@Autowired
	private CurrentUserSessionDAO userSessionDao;
	
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
				List<Tenat> tenatList=landLord.getTenats();
				for(Tenat te:tenatList) {
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
	public Tenat viewTenat(Integer tenatId, String key) throws TenatException ,UserSessionException{
		
		Optional<CurrentUserSession> current=userSessionDao.findByUuId(key);
		if(current.isPresent()) {
			Optional<LandLord> land=landLordDao.findByMobileNo(current.get().getMobileNo());
			if(land.isPresent()) {
				List<Tenat> tenatList=land.get().getTenats();
				for(Tenat te:tenatList) {
					if(te.getTenatId()==tenatId) {
						return te;
					}
				}
				throw new TenatException("Tenat not found");
			}
			throw new UserSessionException("user not found");
		}
		
		throw new UserSessionException("Login first");

	}

	@Override
	public List<Tenat> viewAllTenat(String key) throws TenatException, LandLordException,UserSessionException {
		Optional<CurrentUserSession> current=userSessionDao.findByUuId(key);
		if(current.isPresent()) {
			Optional<LandLord> land=landLordDao.findByMobileNo(current.get().getMobileNo());
			if(land.isPresent()) {
				List<Tenat> tenatList=land.get().getTenats();
				return tenatList;
			}else {
				throw new TenatException("Tenat not found");
			}
		}else {
			throw new UserSessionException("Login first");
		}
	}

}
