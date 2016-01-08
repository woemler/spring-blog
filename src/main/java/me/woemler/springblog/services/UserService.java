package me.woemler.springblog.services;

import me.woemler.springblog.models.User;
import me.woemler.springblog.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author woemler
 */

@Service
public class UserService implements UserDetailsService {

	private static Logger logger  = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired private UserRepository userRepository;

	@Override 
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepository.findByUsername(s);
	}
	
	public void createUser(User user){
		Assert.notNull(user);
		Assert.notNull(user.getUsername());
		Assert.notNull(user.getPassword());
		Assert.notNull(user.getEmail());
		userRepository.save(user);
	}
	
}
