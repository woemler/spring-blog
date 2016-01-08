package me.woemler.springblog.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author woemler
 */

@Document(collection = "users")
public class User implements UserDetails {

	@Id private String userId;
	@NotNull @Indexed(unique = true) private String username;
	@NotNull private String password;
	@NotNull private String name;
	@NotNull private String email;
	@NotNull private Date registrationDate = new Date();
	private Set<String> roles = new HashSet<>();
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean enabled = true;
	private boolean credentialsNonExpired = true;

	public User() { }

	@PersistenceConstructor
	public User(String userId, String username, String password, String name, String email,
			Date registrationDate, Set<String> roles, boolean accountNonExpired, boolean accountNonLocked,
			boolean enabled, boolean credentialsNonExpired) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.registrationDate = registrationDate;
		this.roles = roles;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.enabled = enabled;
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles == null){
			return Collections.emptyList();
		}
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (String role: roles){
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override 
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override 
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override 
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override 
	public boolean isEnabled() {
		return enabled;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	@Override 
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", registrationDate=" + registrationDate +
				", roles=" + roles +
				'}';
	}
	
	
	
}
