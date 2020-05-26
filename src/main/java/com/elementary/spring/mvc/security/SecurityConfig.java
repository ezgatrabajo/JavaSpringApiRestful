package com.elementary.spring.mvc.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.elementary.spring.mvc.security.UserService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


	private UserService userService;

	public SecurityConfig(UserService us ){
		this.userService = us;
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder b= new BCryptPasswordEncoder();
		return b;
	}


	@Override
    protected void configure(final AuthenticationManagerBuilder auth) 
    		throws Exception {
		auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/profile/**").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/v1/pruebas/test1/**").hasRole("MANAGER")
				.antMatchers("/v1/pruebas/test2/**").authenticated()
				.antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
				.antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
				.antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
				.antMatchers("/api/public/users").hasRole("ADMIN")
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/signin")
				.usernameParameter("txtUsername")
				.passwordParameter("txtPassword")
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.and()
				.rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe");
		;
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userService);
		return daoAuthenticationProvider;
	}




	
}
