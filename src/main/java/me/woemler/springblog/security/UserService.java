package me.woemler.springblog.security;

import me.woemler.springblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author woemler
 */

@Service
public class UserService implements UserDetailsService {

	@Autowired private UserRepository userRepository;

	@Override 
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepository.findByUsername(s);
	}
}
