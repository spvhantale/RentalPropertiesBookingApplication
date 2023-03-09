package com.swapnil.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class Config {

	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable()
				.authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/tenant/register", "/landLord/register")
				.permitAll().requestMatchers("/tenant/update", "/tenant/rent/{propertyId}", "/tenant/view","/signIn")
				.hasAnyRole("TENANT", "LANDLORD")
				.requestMatchers("/landLord/update", "/landLord/addProperty", "/landLord/viewTenant/{tenantId}",
						"/landLord/viewTenant")
				.hasRole("LANDLORD").anyRequest().authenticated().and()
				.addFilterAfter(new JwtTokenGenratorFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class).formLogin().and()
				.httpBasic();

		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
}
