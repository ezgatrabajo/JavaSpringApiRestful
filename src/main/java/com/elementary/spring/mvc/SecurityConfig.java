package com.elementary.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.elementary.spring.mvc.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder b= new BCryptPasswordEncoder();
		return b;
	}
	
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) 
    		throws Exception {
		
       auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
       
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().antMatchers("/css/**", 
					"/index",
					"/v1/main/welcome",
					"/v1/categorias/**",
					"/v1/categorias",
					"/v1/categorias/30").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.csrf().disable()
				.formLogin().disable()
				.httpBasic();
	}

	
	
	
}
