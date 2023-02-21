package com.swapnil.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.DTO.LoginDTO;
import net.bytebuddy.utility.RandomString;
import com.swapnil.exception.UserSessionException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.repository.CurrentUserSessionDAO;
import com.swapnil.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private CurrentUserSessionDAO userSessionDao;
	
	
	@Override
	public String loginUser(LoginDTO login) throws UserSessionException {
		
		Optional<CurrentUserSession> curr=userSessionDao.findById(login.getMobileNo());
		
		if(curr.isPresent()) {
			throw new UserSessionException("already login");
		}
		String key=RandomString.make(6);
		CurrentUserSession current=new CurrentUserSession(login.getMobileNo(), key, LocalDateTime.now());
		return userSessionDao.save(current).toString();
	}

	@Override
	public String logOut(String key) throws UserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> curr=userSessionDao.findByUuId(key);
		
		if(curr.isPresent()) {
			userSessionDao.delete(curr.get());
			return "logout";
		}
		throw new UserSessionException("already login");
		
	}

}
