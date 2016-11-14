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

		Object principal = authentication.getPrincipal();
		System.out.println("current principal: " + principal.getClass().getName());
		
		return authentication.getPrincipal().toString(); //	UserDetailsService
	}
}
