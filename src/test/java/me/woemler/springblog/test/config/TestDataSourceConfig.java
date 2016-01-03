package me.woemler.springblog.test.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author woemler
 */

@Configuration
@Import({ TestApplicationConfig.class })
@EnableJpaRepositories("me.woemler.springblog")
@ComponentScan(basePackages = { "me.woemler.springblog.repositories", "me.woemler.springblog.services" })
@EnableTransactionManagement
public class TestDataSourceConfig {
	
	@Autowired private Environment env;
	
	private Properties jdbcProperties(){
		Properties props = new Properties();
		props.setProperty("url", env.getRequiredProperty("jdbc.url"));
		props.setProperty("user", env.getRequiredProperty("jdbc.username"));
		props.setProperty("password", env.getRequiredProperty("jdbc.password"));
		return props;
	}

	private Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}

	@Bean
	public HikariConfig hikariConfig(){
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setPoolName("springHikariCp");
		hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setIdleTimeout(30000);
		hikariConfig.setDataSourceProperties(jdbcProperties());
		return hikariConfig;
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource(){
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		bean.setPackagesToScan("me.woemler.springblog");
		bean.setJpaProperties(hibernateProperties());
		return bean;
	}
	
	@Bean 
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
}
