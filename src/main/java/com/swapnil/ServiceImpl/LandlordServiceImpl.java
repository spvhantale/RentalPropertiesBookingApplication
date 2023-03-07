package com.swapnil.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.swapnil.DTO.LandLordDTO;
import com.swapnil.DTO.PropertyDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.model.LandLord;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;
import com.swapnil.repository.LandLordDAO;
import com.swapnil.repository.PropertyDAO;
import com.swapnil.service.LandlordService;

@Service
public class LandlordServiceImpl implements LandlordService {

	@Autowired
	private LandLordDAO landLordDao;
	@Autowired
	private PropertyDAO propertyDao;
	@Autowired
	private Authentication auth;

	@Override
	public LandLord registerLandLord(LandLordDTO landLord) throws LandLordException {

		Optional<LandLord> opt = landLordDao.findByMobileNumber(landLord.getMobileNumber());
		if (opt.isPresent()) {
			throw new LandLordException("Already register with this number");
		}
		LandLord land = new LandLord(landLord.getFirstName(), landLord.getLastName(), landLord.getMobileNumber(),
				landLord.getAdharNumber(), landLord.getPassword(), "ROLE_" + landLord.getRole().toUpperCase());

		return landLordDao.save(land);
	}

	@Override
	public LandLord updateLandLord(LandLord landLord) throws LandLordException {
		Optional<LandLord> opt = landLordDao.findByMobileNumber(landLord.getMobileNumber());
		if (opt.isPresent()) {
			LandLord land = landLordDao.findByMobileNumber(auth.getName())
					.orElseThrow(() -> new BadCredentialsException("Wrong details"));
			LandLord landOld = opt.get();
			if (land.getLandLordId() == landOld.getLandLordId()) {
				List<Property> propertyList = landLord.getProperties();
				for (Property p : propertyList) {
					p.setAvailability(true);
					p.setLandlord(landLord);
					propertyDao.save(p);
				}
				return landLordDao.save(landLord);
			} else {
				throw new LandLordException("You dont have permission to edit this information");
			}
		}
		throw new LandLordException("LandLord is not present with this mail");

	}

	@Override
	public Property addProperty(PropertyDTO property) throws PropertyException, LandLordException {
		LandLord landlord=landLordDao.findByMobileNumber(auth.getName()).orElseThrow(()-> new LandLordException("Wrong details"));
		
		return null;
	}

	@Override
	public Tenant viewTenant(Integer tenantId) throws TenantException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tenant> viewAllTenant() throws TenantException, LandLordException {
		// TODO Auto-generated method stub
		return null;
	}

}
