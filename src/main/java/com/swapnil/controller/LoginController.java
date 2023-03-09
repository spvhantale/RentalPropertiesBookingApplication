package com.swapnil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.model.LandLord;
import com.swapnil.model.Tenant;
import com.swapnil.repository.LandLordDAO;
import com.swapnil.repository.TenantDAO;

@RestController
public class LoginController {

	@Autowired
	private TenantDAO tenantDao;
	@Autowired
	private LandLordDAO landLordDao;

	@PostMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth) {
		Optional<Tenant> optTenant = tenantDao.findByMobileNumber(auth.getName());
		Optional<LandLord> optLand = landLordDao.findByMobileNumber(auth.getName());
		if (optTenant.isPresent()) {
			Tenant tenant = optTenant.get();

			return new ResponseEntity<String>(tenant.toString(), HttpStatus.ACCEPTED);
		} else if (optLand.isPresent()) {
			LandLord landLord = optLand.get();
			return new ResponseEntity<String>(landLord.toString(), HttpStatus.ACCEPTED);
		} else {
			throw new BadCredentialsException("wrong username or password");
		}
	}
}
