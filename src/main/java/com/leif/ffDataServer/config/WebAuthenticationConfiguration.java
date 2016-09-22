package com.leif.ffDataServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.leif.ffDataServer.domain.Account;
import com.leif.ffDataServer.repositories.AccountRepository;
import com.leif.ffDataServer.services.MongoDBAuthenticationProvider;

@Configuration
public class WebAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter
{
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MongoDBAuthenticationProvider authenticationProvider;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception
	{
		CreateAdminIfNotPresent();
		
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	UserDetailsService userDetailsService()
	{
		return new UserDetailsService()
		{
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
			{
				Account account = accountRepository.findByUsername(username);
				if (account != null)
				{
					return new User(account.getUsername(), account.getPassword(), true, true, true, true, account.getRoles());
				} 
				else
				{
					throw new UsernameNotFoundException("could not find the user '" + username + "'");
				}
			}
		};
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
			throws Exception
	{
		auth.authenticationProvider(authenticationProvider);
	}
	
	private void CreateAdminIfNotPresent()
	{
		// TODO: parameter in application.properties auslagern
		String username = "admin";
		String password = authenticationProvider.getPasswordEncoder().encode("password");
		
		if(accountRepository.findByUsername(username) == null)
		{
			Account root = new Account(username, password);
			root.getRoles().addAll(AuthorityUtils.createAuthorityList("ADMIN", "USER"));
			accountRepository.insert(root);
		}
	}
}
