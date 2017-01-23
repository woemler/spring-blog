package me.woemler.springblog.test.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;

/**
 * @author woemler
 */

@Configuration
@Import({ TestApplicationConfig.class })
@EnableMongoRepositories(basePackages = "me.woemler.springblog.repositories")
public class TestDataSourceConfig {

	@Bean(destroyMethod = "close")
	public Mongo mongo() throws IOException {
		return new EmbeddedMongoBuilder().build();
	}

	@Bean
	public MongoTemplate mongoTemplate(Mongo mongo){
		return new MongoTemplate(mongo, "blog-test");
	}
	
}
