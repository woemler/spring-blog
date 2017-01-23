package me.woemler.springblog.config;

import me.woemler.springblog.models.User;
import me.woemler.springblog.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

/**
 * @author woemler
 */
@Component
public class AdminUserInitializer implements CommandLineRunner {

	@Autowired private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(AdminUserInitializer.class);
	
	public void run(String[] args) throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (userRepository.loadUserByUsername("admin") != null){
			logger.info(String.format("ERROR: User %s already exists!", "admin"));
			return;
		}

		String password = UUID.randomUUID().toString();
        User user = new User();
		user.setUsername("admin");
		user.setPassword(encoder.encode(password));
		user.setRegistrationDate(new Date());
		user.setRoles(new HashSet<>(Arrays.asList(User.ROLE_USER, User.ROLE_ADMIN)));
		userRepository.insert(user);
		
		logger.info(String.format("SUCCESS: Created admin user with username %s and password %s", "admin", password));
		
	}
	
}
