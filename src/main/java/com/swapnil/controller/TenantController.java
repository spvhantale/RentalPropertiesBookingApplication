package com.swapnil.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.DTO.TenantDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.Property;
import com.swapnil.model.Tenant;
import com.swapnil.service.TenantService;


@RestController
@RequestMapping("/tenant")
public class TenantController {

	
	private TenantService tenantService;
	
	public TenantController(TenantService tenantService) {
		// TODO Auto-generated constructor stub
		this.tenantService=tenantService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerTenat(@RequestBody TenantDTO tenat) throws TenantException{
		String message=tenantService.registerTenant(tenat);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateTenant(@RequestBody Tenant tenant,@RequestParam String key) throws TenantException, LandLordException, UserSessionException{
		String message=tenantService.updateTenant(tenant, key);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping("/properties")
	public ResponseEntity<List<Property>> viewProperties(@RequestParam String key) throws PropertyException, UserSessionException{
		List<Property> propertyList=tenantService.viewProperties(key);
		
		return new ResponseEntity<List<Property>>(propertyList, HttpStatus.OK);
		
	}
	@PutMapping("/rentProperty/{propertyId}")
	public ResponseEntity<String> rentProperty(@PathVariable("propertyId") Integer propertyId,@RequestParam String key) throws PropertyException, TenantException, UserSessionException{
		
		String message=tenantService.rentProperty(propertyId, key);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
}
