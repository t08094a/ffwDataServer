package com.leif.ffDataServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.leif.ffDataServer.domain.Account;
import com.leif.ffDataServer.repositories.AccountRepository;

@Service
public class MongoDBAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{
	@Autowired
	private AccountRepository accountRepository;
	
	private PasswordEncoder encoder = new BCryptPasswordEncoder();
		
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) 
			throws AuthenticationException
	{
        if (authentication.getCredentials() == null) 
        {
            logger.debug("Authentication failed: no credentials provided");

            String msg = messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials");
            throw new BadCredentialsException(msg);
        }

        String presentedPassword = authentication.getCredentials().toString();
        
        boolean matches = encoder.matches(presentedPassword, userDetails.getPassword());
        if (!matches)
        {
            logger.debug("Authentication failed: password does not match stored value");

            String msg = messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials");
            throw new BadCredentialsException(msg);
        }
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException
	{
		UserDetails loadedUser = null;

		Account account = accountRepository.findByUsername(username);

		if (account != null)
		{
			loadedUser = new User(account.getUsername(), account.getPassword(), account.getRoles());
		} 
		else
		{
			throw new InternalAuthenticationServiceException("The account for user '" + username + "' does not exist");
		}

		return loadedUser;
	}
 
	@Bean
    public PasswordEncoder getPasswordEncoder() 
    {
        return encoder;
    }                      
}
