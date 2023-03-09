package com.swapnil.Config;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenGenratorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication!=null) {
			SecretKey key=Keys.hmacShaKeyFor(SecurityConstant.JWT_key.getBytes());
			String jwt=
					Jwts.builder()
					.setIssuer("swapnil")
					.setSubject("JWT Token")
					.claim("username", authentication.getName())
					.claim("authorities", populateAuthorites(authentication.getAuthorities()))
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime()+300000))
					.signWith(key)
					.compact();
			response.setHeader(SecurityConstant.JWT_HEADER, jwt);
		}
		filterChain.doFilter(request, response);
	}

	private String populateAuthorites(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritySet = new HashSet<>();

		for (GrantedAuthority authorites : collection) {
			authoritySet.add(authorites.getAuthority());
		}
		return String.join(",", authoritySet);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		return !request.getServletPath().equals("/signIn");
	}

}
