package com.up.tx.manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "txManagerEntityManagerFactory", transactionManagerRef = "txManagerTransactionManager",
basePackages = {"com.up.tx.manager.repository"})
public class MySqlManagerTxConfig {
	@Autowired
	private Environment env;

	@Bean(name = "txManagerDataSource")
	public DataSource txManagerDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("txManager.datasource.url"));
		dataSource.setUsername(env.getProperty("txManager.datasource.username"));
		dataSource.setPassword(env.getProperty("txManager.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("txManager.datasource.driver-class-name"));
		
		return dataSource;
	}

	@Bean(name = "txManagerEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(txManagerDataSource());
		em.setPackagesToScan("com.up.tx.manager.model");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("txManager.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show-sql", env.getProperty("txManager.jpa.show-sql"));
		properties.put("hibernate.dialect", env.getProperty("txManager.jpa.database-platform"));
		
		em.setJpaPropertyMap(properties);
		
		return em;
		
	}

	@Bean(name = "txManagerTransactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		
		return transactionManager;
	}

}

