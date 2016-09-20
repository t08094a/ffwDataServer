package com.leif.ffDataServer.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityAuditorAware implements AuditorAware<String>
{

	@Override
	public String getCurrentAuditor()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated())
		{
			return null;
		}

//		Object principal = authentication.getPrincipal();
//		return authentication.getPrincipal().toString();
		return "John Doe";
	}
//	UserDetailsService
//	
//	http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.auditing
}
