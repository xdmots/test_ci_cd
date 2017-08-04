package com.tesco.sample.service.notes;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractReactiveCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;

@Configuration
@EnableReactiveCouchbaseRepositories
public class DBConfig extends AbstractReactiveCouchbaseConfiguration {

	@Override
	protected List<String> getBootstrapHosts() {
		String dbHost = System.getenv().get("DB_HOST_ADDR");
		System.out.println("dbHost ...... " + dbHost);
		return Arrays.asList(dbHost);
	}

	@Override
	protected String getBucketName() {
		return "default";
	}

	@Override
	protected String getBucketPassword() {
		return "";
	}
}
