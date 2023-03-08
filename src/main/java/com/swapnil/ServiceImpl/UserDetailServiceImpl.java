package com.swapnil.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.swapnil.model.LandLord;
import com.swapnil.model.Tenant;
import com.swapnil.repository.LandLordDAO;
import com.swapnil.repository.TenantDAO;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private TenantDAO tenantDao;
	@Autowired
	private LandLordDAO landLordDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Tenant> optTenant = tenantDao.findByMobileNumber(username);

		Optional<LandLord> optLandLord=landLordDao.findByMobileNumber(username);
		if(optTenant.isPresent()) {
			Tenant tenant=optTenant.get();
			List<GrantedAuthority> authorities=new ArrayList<>();
			SimpleGrantedAuthority sga=new SimpleGrantedAuthority(tenant.getRole());
			authorities.add(sga);
			return new User(tenant.getMobileNumber(), tenant.getPassword(), true, true, true, true, authorities);
		}else if(optLandLord.isPresent()) {
			LandLord landLord=optLandLord.get();
			List<GrantedAuthority> authorities=new ArrayList<>();
			SimpleGrantedAuthority sga=new SimpleGrantedAuthority(landLord.getRole());
			authorities.add(sga);
			return new User(landLord.getMobileNumber(), landLord.getPassword(), true, true, true, true, authorities);
		}else {
			throw new BadCredentialsException("User Detail not found with this "+username);
		}
	
	}

}
