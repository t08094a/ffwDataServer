package com.leif.ffDataServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.leif.ffDataServer.services.SpringSecurityAuditorAware;

@Configuration
@EnableMongoAuditing
public class AuditConfiguration
{
	@Bean
	public AuditorAware<String> myAuditorProvider()
	{
		return new SpringSecurityAuditorAware();
	}
}
