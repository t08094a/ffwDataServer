package com.leif.ffDataServer.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.*;

public abstract class Auditable
{
	@CreatedBy
	private String createdUser;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedBy
	private String lastModifiedByUser;
	
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	@Version
	private Long version;

	/**
	 * @return the createdUser
	 */
	public String getCreatedUser()
	{
		return createdUser;
	}

	/**
	 * @return the createdDate
	 */
	public LocalDateTime getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * @return the lastModifiedByUser
	 */
	public String getLastModifiedByUser()
	{
		return lastModifiedByUser;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public LocalDateTime getLastModifiedDate()
	{
		return lastModifiedDate;
	}

	/**
	 * @return the version
	 */
	public Long getVersion()
	{
		return version;
	}
}
