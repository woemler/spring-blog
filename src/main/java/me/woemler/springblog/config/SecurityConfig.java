package me.woemler.springblog.config;

/**
 * @author woemler
 * 
*/

public class SecurityConfig {

//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackages = "me.woemler.springblog.security")
//public class SecurityConfig extends WebSecurityConfigurerAdapter  {
//
//	@Autowired
//	private UserDetailsService userService;
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//				.userDetailsService(userService)
//				.passwordEncoder(new BCryptPasswordEncoder());
//	}
//
//	@Override 
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.authorizeRequests()
//						.anyRequest().authenticated()
//						.and()
//				.formLogin()
//						.loginPage("/login")
//						.permitAll();
//	}

	//	@Configuration
//	@Order(1)
//	@ComponentScan(basePackages = "me.woemler.springblog.security")
//	public static class ApiTokenAuthenticationConfig extends WebSecurityConfigurerAdapter {
//
//		@Autowired
//		private UserDetailsService userService;
//
//		@Autowired
//		private Environment env;
//
//		@Bean
//		public TokenOperations tokenUtils(){
//			return new BasicTokenUtils(env.getRequiredProperty("token.key"));
//		}
//
//		@Bean
//		public AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter(){
//			return new AuthenticationTokenProcessingFilter(tokenUtils(), userService);
//		}
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http
//					.sessionManagement()
//					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//					.and()
//					.addFilterBefore(authenticationTokenProcessingFilter(),
//							UsernamePasswordAuthenticationFilter.class)
//					.antMatcher(env.getRequiredProperty("springblog.api.url"))
//					.authorizeRequests()
//					.antMatchers(HttpMethod.GET, env.getRequiredProperty("api.url")).permitAll()
//					.antMatchers(HttpMethod.POST, env.getRequiredProperty("api.url")).fullyAuthenticated()
//					.antMatchers(HttpMethod.PUT, env.getRequiredProperty("api.url")).fullyAuthenticated()
//					.antMatchers(HttpMethod.DELETE, env.getRequiredProperty("api.url")).fullyAuthenticated()
//					.antMatchers(HttpMethod.PATCH, env.getRequiredProperty("api.url")).fullyAuthenticated()
//					.antMatchers(HttpMethod.OPTIONS, env.getRequiredProperty("api.url")).permitAll()
//					.antMatchers(HttpMethod.HEAD, env.getRequiredProperty("api.url")).permitAll()
//					.and()
//					.csrf().disable();
//		}
//	}

//	@Configuration
//	@Order(2)
//	public static class BasicWebSecurtiyConfig extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http
//					.authorizeRequests()
//					.anyRequest().permitAll()
//					.and().sessionManagement()
//					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//					.and().httpBasic()
//					.and().csrf().disable();
//		}
//	}
	
}
