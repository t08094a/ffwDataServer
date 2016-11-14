package com.leif.ffDataServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.leif.ffDataServer.repositories.PersonRepository;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
@PropertySource("classpath:application.properties")
@EnableMongoRepositories(basePackageClasses=PersonRepository.class)
public class MongoConfig extends AbstractMongoConfiguration
{
	@Autowired
	private Environment environment;

	@Override
	protected String getDatabaseName()
	{
		String database = environment.getProperty("spring.data.mongodb.database");
		return database;
	}

	@Override
	public Mongo mongo() throws Exception
	{
		String host = environment.getProperty("spring.data.mongodb.host", "localhost");
		Integer port = environment.getProperty("spring.data.mongodb.port", Integer.class, 27017);
		
		ServerAddress serverAddress = new ServerAddress(host, port);
		
		//MongoCredential credential = MongoCredential.createMongoCRCredential("vivek","sample","vivek".toCharArray());
		//MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(4).socketKeepAlive(true).build();
		//Mongo mongo = new MongoClient(serverAddress, Arrays.asList(credential),options);
		
		MongoClient client = new MongoClient(serverAddress);
		client.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		return client;
	}

	@Override
	protected String getMappingBasePackage()
	{
		return "com.leif.ffDataServer.domain";
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception
	{
		MongoTemplate template = new MongoTemplate(mongo(), getDatabaseName());
				
		return template;
	}
}
