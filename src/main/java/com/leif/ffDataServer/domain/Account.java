package com.leif.ffDataServer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document(collection = "accounts")
@TypeAlias("account")
public class Account extends AbstractDocument
{
	@NotEmpty
	@Indexed(unique=true)
	private String					username;

	@NotEmpty
	private String					password;

	@JsonSerialize(contentUsing = GrantedAuthoritySerializer.class)
	@JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)
	private List<GrantedAuthority>	roles;

	public Account()
	{
	}

	public Account(String username, String password)
	{
		Assert.hasText(username);
		Assert.hasLength(password, "The password must have a minimum length of 7");

		this.username = username;
		this.password = password;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public List<GrantedAuthority> getRoles()
	{
		if(roles == null)
		{
			roles = new ArrayList<>();
		}
		
		return roles;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public int hashCode()
	{
		return username.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		if (!(obj instanceof Account))
		{
			return false;
		}

		Account other = (Account) obj;
		return username == other.username;
	}

	@Override
	public String toString()
	{
		String roleText = roles.stream().map(role -> role.getAuthority()).collect(Collectors.joining(", "));

		return "username: " + username + ", roles: [" + roleText + "]";
	}
}
