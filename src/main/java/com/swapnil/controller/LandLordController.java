package com.swapnil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.swapnil.service.LandLordService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/landlord")
public class LandLordController {

	@Autowired
	private LandLordService landLordService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerLandLord(@RequestBody LandLordDTO landlord) throws LandLordException{
		String message=landLordService.registerLandLord(landlord);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateLandLord(@RequestBody LandLord landlord,@RequestParam String key) throws LandLordException, UserSessionException{
		String message=landLordService.updateLandLord(landlord,key);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	@PutMapping("/addProperty")
	public ResponseEntity<String> addProperty(@RequestBody PropertyDTO property,@RequestParam String key) throws PropertyException, UserSessionException, LandLordException{
		
		String message=landLordService.addProperty(property, key);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	@GetMapping("/view/{tenantId}")
	public ResponseEntity<Tenant> viewTenant(@PathVariable("tenantId") Integer tenantId,@RequestParam String key) throws PropertyException, UserSessionException, LandLordException, TenantException{
		
		Tenant tenant=landLordService.viewTenant(tenantId, key);
		
		return new ResponseEntity<Tenant>(tenant, HttpStatus.OK);
	}
	@GetMapping("/viewAll")
	public ResponseEntity<List<Tenant>> viewAllTenant(@RequestParam String key) throws PropertyException, UserSessionException, LandLordException, TenantException{
		
		List<Tenant> tenantList=landLordService.viewAllTenant(key);
		
		return new ResponseEntity<List<Tenant>>(tenantList, HttpStatus.OK);
	}
	
	
}
