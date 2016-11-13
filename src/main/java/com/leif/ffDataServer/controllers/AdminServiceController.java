package com.leif.ffDataServer.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leif.ffDataServer.domain.Account;
//import com.leif.ffDataServer.domain.Role;
import com.leif.ffDataServer.repositories.AccountRepository;

//@Secured(Role.ADMIN)
@RestController
@RequestMapping("/adminservice")
public class AdminServiceController
{
	public AdminServiceController()
	{
		System.out.println(">>> ctor AdminServiceController");
	}
	
	@Autowired
	private AccountRepository accountRepository;
	
//	@Autowired
//	private PasswordEncoder encoder;

//	@Secured(Role.ADMIN)
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED )
	public Account create(@RequestBody Account account)
	{
//		String password = account.getPassword();
//		
//		String encodedPassword = encoder.encode(password);
//		account.setPassword(encodedPassword);
				
		return accountRepository.save(account);
	}
	
//	@Secured(Role.ADMIN)
//	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "{username}")
	@ResponseStatus(HttpStatus.OK)
	public Account get(@PathVariable("username") String username)
	{
		return accountRepository.findByUsername(username);
	}
	
//	@Secured(Role.ADMIN)
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Account> getAll()
	{
		System.out.println(">>> getAll");
		return accountRepository.findAll();
	}
	
//	@Secured(Role.ADMIN)
	@RequestMapping("/roles")
	public ResponseEntity<Map<String, Object>> getCurrentRoles(@AuthenticationPrincipal UserDetails userDetails)
	{
		Account account = accountRepository.findByUsername(userDetails.getUsername());
		
		if(account == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("username", account.getUsername());
		model.put("roles", account.getRoles());
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
	
//	@Secured(Role.ADMIN)
	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	@ResponseStatus(HttpStatus.OK)
	public Account update(@PathVariable("id") String username, @RequestBody Account account)
	{
		Account update = accountRepository.findByUsername(username);
		
//		String encodedPassword = encoder.encode(account.getPassword());
//		update.setPassword(encodedPassword);
		
		return accountRepository.save(update);
	}

//	@Secured(Role.ADMIN)
	@RequestMapping(method = RequestMethod.DELETE, value = "{username}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("username") String username)
	{
		accountRepository.deleteByUsername(username);
	}
}
