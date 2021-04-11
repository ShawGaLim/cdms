package com.cdms.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cdms.dao.UserMapper;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper um;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetailsImpl userDetails = new UserDetailsImpl();
		userDetails.setUser(um.selectUserById(username));
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		if(userDetails.getUser()==null) {
			throw new UsernameNotFoundException("Not found username(id):" + username);
		} else {
			if(userDetails.getUser().getIdentity().equals("学生")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_student"));
			} else if(userDetails.getUser().getIdentity().equals("教师")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_teacher"));
			} else if(userDetails.getUser().getIdentity().equals("管理员")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
			} else {
				authorities.add(new SimpleGrantedAuthority("ROLE_nobody"));
			}
			return new User(username,userDetails.getUser().getPassword(),authorities);
		}
	}

}
