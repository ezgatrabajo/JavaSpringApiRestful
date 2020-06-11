package com.elementary.spring.mvc.security;


import com.elementary.spring.mvc.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.elementary.spring.mvc.security.UserService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private UsuarioRepository userRepository;
	private UserService userService;

	public SecurityConfig(UserService us , UsuarioRepository userRepository ){
		this.userRepository = userRepository;
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
            	.csrf().disable()

				//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
				.authorizeRequests()

				// configure access rules
				//tutorial
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.POST, "/v1/mercadopago/procesar_pago").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers("/api/public/admin/*").hasRole("ADMIN")
				//Mios
				.antMatchers("/profile/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/v1/categorias/**").hasRole("MANAGER")
				.antMatchers("/v1/marcas/**").hasRole("ADMIN")
				.antMatchers("/mercadopago/**").permitAll()


				.anyRequest().authenticated()
                //.httpBasic().and().csrf().disable();
				/*
				.and()

				.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/signin")
				.usernameParameter("txtUsername")
				.passwordParameter("txtPassword")
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
				.and()
				.rememberMe()
				.tokenValiditySeconds(2592000).key("mySecret!")
				.rememberMeParameter("checkRememberMe");
 				*/

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
