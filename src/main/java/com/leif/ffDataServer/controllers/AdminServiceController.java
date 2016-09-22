package com.leif.ffDataServer.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leif.ffDataServer.domain.Account;
import com.leif.ffDataServer.repositories.AccountRepository;

@RestController
@Secured("ADMIN")
@RequestMapping("/adminservice")
public class AdminServiceController
{
	@Autowired
	private AccountRepository accountRepository;

	@Secured("ADMIN")
	@RequestMapping("/adminservice")
	public ResponseEntity<Map<String, Object>> getRoles(@AuthenticationPrincipal UserDetails userDetails)
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
}
