package com.cdms.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.cdms.security.UserDetailsServiceImpl;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Autowired
    UserDetailsServiceImpl userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		if(!userDetails.getPassword().equals(password)) {
			throw new BadCredentialsException("密码错误");
		}
		
		return  new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());	
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
