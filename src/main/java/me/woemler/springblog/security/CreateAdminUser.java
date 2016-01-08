package me.woemler.springblog.security;

import me.woemler.springblog.config.DataSourceConfig;
import me.woemler.springblog.models.User;
import me.woemler.springblog.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * @author woemler
 */
public class CreateAdminUser {
	
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	
	public static void main(String[] args) throws Exception {
		CreateAdminUser main = new CreateAdminUser();
		main.run(args);
	}
	
	public void run(String[] args) throws Exception {
		
		UserService userService = ctx.getBean(UserService.class);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if (args.length != 3){
			System.out.println("ERROR: This script expects 3 arguments: username password email");
			return;
		}
//		if (!args[1].equals(args[2])){
//			System.out.println("ERROR: Input passwords do not match.");
//			return;
//		}
		if (userService.loadUserByUsername(args[0]) != null){
			System.out.println(String.format("ERROR: User %s already exists!", args[0]));
			return;
		}

		User user = new User();
		user.setUsername(args[0]);
		user.setPassword(encoder.encode(args[1]));
		user.setEmail(args[2]);
		user.setRegistrationDate(new Date());
		user.setRoles(new HashSet<>(Arrays.asList(User.ROLE_USER, User.ROLE_ADMIN)));
		userService.createUser(user);
		
		System.out.println(String.format("SUCCESS: Created admin user with username %s", args[0]));
		
	}
	
}
