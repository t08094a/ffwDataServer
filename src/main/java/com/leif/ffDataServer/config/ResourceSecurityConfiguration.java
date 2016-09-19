package com.leif.ffDataServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalAuthentication
@EnableWebSecurity
public class ResourceSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/api/**").authenticated()
			.and()
			.formLogin()
				.defaultSuccessUrl("/api", true)
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/logout/")
				.invalidateHttpSession(true)
				.logoutUrl("/")
				.deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
				.csrf();
	}
}
