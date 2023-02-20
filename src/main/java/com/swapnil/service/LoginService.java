package com.swapnil.service;

import com.swapnil.DTO.LoginDTO;
import com.swapnil.exception.UserSessionException;

public interface LoginService {

	public String loginUser(LoginDTO login)throws UserSessionException;
	public String logiOut(String key) throws UserSessionException;
}
