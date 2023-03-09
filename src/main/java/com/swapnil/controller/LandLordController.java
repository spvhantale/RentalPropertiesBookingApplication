package com.swapnil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.DTO.LandLordDTO;
import com.swapnil.DTO.PropertyDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.model.LandLord;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;
import com.swapnil.service.LandlordService;

@RestController
@RequestMapping("/landLord")
public class LandLordController {

	@Autowired
	private LandlordService landLordService;
	@Autowired
	private PasswordEncoder pEncoder;

	@PostMapping("/register")
	public ResponseEntity<LandLord> registerLandLord(@RequestBody LandLordDTO landLord) throws LandLordException {
		landLord.setPassword(pEncoder.encode(landLord.getPassword()));

		LandLord landLordNew = landLordService.registerLandLord(landLord);

		return new ResponseEntity<LandLord>(landLordNew, HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity<LandLord> updateLandLord(@RequestBody LandLord landLord) throws LandLordException {
		landLord.setPassword(pEncoder.encode(landLord.getPassword()));

		LandLord landLordNew = landLordService.updateLandLord(landLord);

		return new ResponseEntity<LandLord>(landLordNew, HttpStatus.ACCEPTED);

	}

	@PutMapping("/addProperty")
	public ResponseEntity<Property> addProperty(@RequestBody PropertyDTO property,Authentication auth)
			throws PropertyException, LandLordException {

		Property propertyNew = landLordService.addProperty(property,auth);

		return new ResponseEntity<Property>(propertyNew, HttpStatus.ACCEPTED);

	}

	@GetMapping("/viewTenat/{tenantId}")
	public ResponseEntity<Tenant> viewTenant(@PathVariable Integer tenantId,Authentication auth) throws TenantException, LandLordException {

		Tenant tenant = landLordService.viewTenant(tenantId,auth);

		return new ResponseEntity<Tenant>(tenant, HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewTenat")
	public ResponseEntity<List<Tenant>> viewAllTenant(Authentication auth) throws TenantException, LandLordException {

		List<Tenant> tenantList = landLordService.viewAllTenant(auth);

		return new ResponseEntity<List<Tenant>>(tenantList, HttpStatus.ACCEPTED);
	}
}
