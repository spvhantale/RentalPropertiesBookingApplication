package com.swapnil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.DTO.TenantDTO;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;
import com.swapnil.service.TenantService;

@RestController
@RequestMapping("/tenat")
public class TenantController {

	@Autowired
	private TenantService tenantService;
	@Autowired
	private PasswordEncoder pEncoder;

	@PostMapping("/register")
	public ResponseEntity<Tenant> registerTenant(@RequestBody TenantDTO tenant) throws TenantException {
		tenant.setPassword(pEncoder.encode(tenant.getPassword()));

		Tenant tenantNew = tenantService.registerTenant(tenant);

		return new ResponseEntity<Tenant>(tenantNew, HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity<Tenant> updateTenant(@RequestBody Tenant tenant) throws TenantException {

		tenant.setPassword(pEncoder.encode(tenant.getPassword()));

		Tenant tenantNew = tenantService.updateTenant(tenant);

		return new ResponseEntity<Tenant>(tenantNew, HttpStatus.ACCEPTED);

	}

	@GetMapping("/view")
	public ResponseEntity<List<Property>> viewProperties() throws TenantException, PropertyException {

		List<Property> properties = tenantService.viewProperties();
		return new ResponseEntity<List<Property>>(properties, HttpStatus.ACCEPTED);
	}

	@PutMapping("/rent/{propertyId}")
	public ResponseEntity<String> rentProeprty(@PathVariable("propertyId") Integer propertyId)
			throws PropertyException, TenantException {

		String message = tenantService.rentProperty(propertyId);

		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}
}
