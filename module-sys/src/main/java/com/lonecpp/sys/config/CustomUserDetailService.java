package com.lonecpp.sys.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {
	static final Logger LOG = Logger.getLogger(CustomUserDetailService.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.info("登录用户名: " + username);
		if(!"admin".equals(username)) {
			throw new UsernameNotFoundException(username + "用户未找到");
		}
		
		return new User(username, passwordEncoder.encode("123"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
