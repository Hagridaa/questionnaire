package com.app.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.questionnaire.web.UserDetailServiceImpl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
//aktivoi spring security web service tuen

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 private UserDetailServiceImpl userDetailsService;
	   

//WebSecurityConfig class contains a method
//configure(HttpSecurity)that defines which URL
//paths are secured and the path for login form
@Override
protected void configure(HttpSecurity http) throws Exception{
	http
	.authorizeRequests().antMatchers("/css/**","/webjars/**", "/adduser").permitAll(); // Enable css when logged out
//	   .anyRequest().authenticated()
//    .and()
//	.authorizeRequests().antMatchers("/delete/{id}","/edit/{id}").hasAuthority("ADMIN")
//	.and()
//	.authorizeRequests().antMatchers("/addOPTION").hasAuthority("USER")
//	.and()
//	.authorizeRequests().antMatchers("/questionnairelist").hasAuthority("USER")
// 	.and()
// .formLogin().
//	.and
// loginPage("/login")
//     .defaultSuccessUrl("/questionnairelist")
//     .and()
// .logout()
// .logoutSuccessUrl("/")
//     .permitAll();	
//	
}

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

}

@Bean 
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

@Bean
public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	provider.setPasswordEncoder(passwordEncoder());
	provider.setUserDetailsService(userDetailsService);
	return provider;
}
}


