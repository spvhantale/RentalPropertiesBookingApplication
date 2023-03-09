package com.swapnil.ServiceImpl;

import java.util.List;
import java.util.Optional;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

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

	@Override
	public LandLord registerLandLord(LandLordDTO landLord) throws LandLordException {

		Optional<LandLord> opt = landLordDao.findByMobileNumber(landLord.getMobileNumber());
		if (opt.isPresent()) {
			throw new LandLordException("Already register with this number");
		}
		LandLord land = new LandLord(landLord.getFirstName(), landLord.getLastName(), landLord.getMobileNumber(),
				landLord.getAdharNumber(), landLord.getPassword(), "ROLE_LANDLORD");

		return landLordDao.save(land);
	}

	@Override
	public LandLord updateLandLord(LandLord landLord) throws LandLordException {
		Optional<LandLord> opt = landLordDao.findByMobileNumber(landLord.getMobileNumber());
		if (opt.isPresent()) {

			landLord.setRole("ROLE_LANDLORD");
			List<Property> propertyList = landLord.getProperties();
			for (Property p : propertyList) {
				p.setAvailability(true);
				p.setLandlord(landLord);
				propertyDao.save(p);
			}
			return landLordDao.save(landLord);
		}

		throw new LandLordException("LandLord is not present with this mail");

	}

	@Override
	public Property addProperty(PropertyDTO property, Authentication auth) throws PropertyException, LandLordException {
		LandLord landLord = landLordDao.findByMobileNumber(auth.getName())
				.orElseThrow(() -> new LandLordException("Wrong details"));

		Property propertyNew = new Property(property.getCity(), property.getStreetNo(), property.getState(),
				property.getBedRoom(), property.getHall(), property.getRent(), false, landLord);
		return propertyDao.save(propertyNew);

	}

	@Override
	public Tenant viewTenant(Integer tenantId, Authentication auth) throws TenantException, LandLordException {
		LandLord landLord = landLordDao.findByMobileNumber(auth.getName())
				.orElseThrow(() -> new LandLordException("Wrong details"));

		List<Tenant> tenantList = landLord.getTenants();
		Stream<Tenant> stream = tenantList.stream().filter(s -> s.getTenatId() == tenantId);
		Tenant tenant = (Tenant) stream;
		return tenant;
	}

	@Override
	public List<Tenant> viewAllTenant(Authentication auth) throws TenantException, LandLordException {

		List<Tenant> tenantList = landLordDao.getAllTenant(auth.getName());
		if (tenantList.isEmpty()) {
			throw new TenantException("Tenant is not present");
		}
		return tenantList;
	}

}
