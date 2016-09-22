package com.leif.ffDataServer.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class ResourceSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/api/**").authenticated()
			.antMatchers("/manage/**").authenticated()
			.and()
				.formLogin().permitAll()
			.and().logout()
				.logoutUrl("/logout/").logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").invalidateHttpSession(true).deleteCookies("JSESSIONID")
			.and()
				.csrf().disable();
	}
}
