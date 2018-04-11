package com.s3corp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static String REALM = "MY_APP_REALM";

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/admin/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/account/**").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.POST).hasAnyRole("USER")
		.antMatchers(HttpMethod.PUT).hasAnyRole("USER")
		.antMatchers(HttpMethod.DELETE).hasAnyRole("USER")
		.antMatchers(HttpMethod.GET).permitAll()
		.anyRequest().permitAll()
		.and().httpBasic()
		.realmName(REALM)
		.authenticationEntryPoint(customAuthenticationEntryPoint);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}
}