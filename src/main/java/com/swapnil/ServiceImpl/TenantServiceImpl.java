package com.swapnil.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.swapnil.DTO.TenantDTO;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;
import com.swapnil.repository.PropertyDAO;
import com.swapnil.repository.TenantDAO;
import com.swapnil.service.TenantService;

@Service
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantDAO tenantDao;
	@Autowired
	private PropertyDAO propertyDao;
	@Autowired
	private Authentication auth;

	@Override
	public Tenant registerTenant(TenantDTO tenant) throws TenantException {

		Optional<Tenant> opt = tenantDao.findByMobileNumber(tenant.getMobileNumber());
		if (opt.isPresent()) {
			throw new TenantException("Already register with this name");
		}
		Tenant tenantOrg = new Tenant(tenant.getFirstName(), tenant.getLastName(), tenant.getMobileNumber(),
				tenant.getAdharNumber(), tenant.getPassword(), "ROLE_" + tenant.getRole().toUpperCase());

		return tenantDao.save(tenantOrg);
	}

	@Override
	public Tenant updateTenant(Tenant tenant) throws TenantException {

		Optional<Tenant> opt = tenantDao.findByMobileNumber(tenant.getMobileNumber());
		if (opt.isPresent()) {
			Tenant tenLog = tenantDao.findByMobileNumber(auth.getName())
					.orElseThrow(() -> new BadCredentialsException("wrong details"));
			if (tenLog.getTenatId() == opt.get().getTenatId()) {
				Property property = tenant.getProperty();
				if (property != null) {
					tenant.setRole("ROLE_" + tenant.getRole().toUpperCase());
					property.setTenant(tenant);
					propertyDao.save(property);
					return tenantDao.save(tenant);
				} else {
					return tenantDao.save(tenant);
				}
			} else {
				throw new TenantException("You dont have permission to update use same details");
			}
		}
		throw new TenantException("Already register with this name");

	}

	@Override
	public List<Property> viewProperties() throws TenantException, PropertyException {

		List<Property> properties = propertyDao.findAll();
		if (properties.isEmpty()) {
			throw new PropertyException("Properties not present");
		} else {
			return properties.stream().filter(s -> s.isAvailability()).toList();
		}

	}

	@Override
	public Tenant rentProperty(Integer propertyId) throws PropertyException, TenantException {

		Tenant tenant = tenantDao.findByMobileNumber(auth.getName())
				.orElseThrow(() -> new BadCredentialsException("Wrong user"));

		Property newProperty = propertyDao.findById(propertyId)
				.orElseThrow(() -> new PropertyException("Property Id is wrong"));

		if (newProperty.isAvailability()) {
			Property oldProperty = tenant.getProperty();
			oldProperty.setAvailability(true);
			oldProperty.setTenant(null);
			propertyDao.save(oldProperty);
			newProperty.setAvailability(false);
			newProperty.setTenant(tenant);
			propertyDao.save(newProperty);
			return tenantDao.save(tenant);

		} else {
			throw new PropertyException("Already user rent this property");
		}
	}

}
