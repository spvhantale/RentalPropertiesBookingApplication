package com.swapnil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.DTO.LoginDTO;
import com.swapnil.exception.UserSessionException;
import com.swapnil.service.LoginService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/log")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/In")
	public ResponseEntity<String> login(@RequestBody LoginDTO login) throws UserSessionException{
		
		String message=loginService.loginUser(login);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PostMapping("/Out")
	public ResponseEntity<String> logout(@RequestParam String key) throws UserSessionException{
		
		String message=loginService.logOut(key);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
}
