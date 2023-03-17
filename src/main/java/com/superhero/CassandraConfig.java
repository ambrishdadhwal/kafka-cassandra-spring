package com.superhero;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration
{

	@Value("${spring.data.cassandra.keyspace-name: simple_crud}")
	private String keyspace;

	@Value("${spring.data.cassandra.contact-points: localhost}")
	private String contactPoint;

	@Value("${spring.data.cassandra.port: 9042}")
	private int port;

	@Override
	public String getContactPoints()
	{
		return contactPoint;
	}

	@Override
	protected int getPort()
	{
		return port;
	}

	@Override
	public SchemaAction getSchemaAction()
	{
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations()
	{
		return Collections.singletonList(CreateKeyspaceSpecification
			.createKeyspace(keyspace).ifNotExists()
			.with(KeyspaceOption.DURABLE_WRITES, true)
			.withSimpleReplication());
	}

	/*
	 * Creating keyspace if does not exists
	 */
	@Override
	protected List<String> getStartupScripts()
	{
		return Collections.singletonList("CREATE KEYSPACE IF NOT EXISTS "
			+ keyspace + " WITH replication = {"
			+ " 'class': 'SimpleStrategy', "
			+ " 'replication_factor': '3' " + "};");

	}

	@Override
	protected String getLocalDataCenter()
	{
		return "datacenter1";
	}

	@Override
	protected String getKeyspaceName()
	{
		return keyspace;
	}

	@Override
	public String[] getEntityBasePackages()
	{
		return new String[]
		{"com.superhero"};
	}
}
