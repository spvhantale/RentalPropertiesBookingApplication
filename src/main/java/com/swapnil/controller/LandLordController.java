package com.swapnil.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.DTO.LandLordDTO;
import com.swapnil.DTO.PropertyDTO;
import com.swapnil.exception.LandLordException;
import com.swapnil.exception.PropertyException;
import com.swapnil.exception.TenantException;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.LandLord;
import com.swapnil.model.Tenant;
import com.swapnil.service.LandService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/landlord")
public class LandLordController {

	private LandService landService;
	
	public LandLordController(LandService landService) {
		// TODO Auto-generated constructor stub
		this.landService=landService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerLandLord(@RequestBody LandLordDTO landlord) throws LandLordException{
		String message=landService.registerLandLord(landlord);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateLandLord(@RequestBody LandLord landlord,@RequestParam String key) throws LandLordException, UserSessionException{
		String message=landService.updateLandLord(landlord,key);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	@PutMapping("/addProperty")
	public ResponseEntity<String> addProperty(@RequestBody PropertyDTO property,@RequestParam String key) throws PropertyException, UserSessionException, LandLordException{
		
		String message=landService.addProperty(property, key);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	@GetMapping("/view/{tenantId}")
	public ResponseEntity<Tenant> viewTenant(@PathVariable("tenantId") Integer tenantId,@RequestParam String key) throws PropertyException, UserSessionException, LandLordException, TenantException{
		
		Tenant tenant=landService.viewTenant(tenantId, key);
		
		return new ResponseEntity<Tenant>(tenant, HttpStatus.OK);
	}
	@GetMapping("/viewAll")
	public ResponseEntity<List<Tenant>> viewAllTenant(@RequestParam String key) throws PropertyException, UserSessionException, LandLordException, TenantException{
		
		List<Tenant> tenantList=landService.viewAllTenant(key);
		
		return new ResponseEntity<List<Tenant>>(tenantList, HttpStatus.OK);
	}
	
	
}
