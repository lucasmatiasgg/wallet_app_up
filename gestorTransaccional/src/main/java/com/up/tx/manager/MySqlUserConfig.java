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
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory", transactionManagerRef = "userTransactionManager",
basePackages = {"com.up.tx.manager.login.repository"})
public class MySqlUserConfig {

    @Autowired
    private Environment env;

    @Bean(name = "userDataSource")
    public DataSource userDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("user.datasource.url"));
        dataSource.setUsername(env.getProperty("user.datasource.username"));
        dataSource.setPassword(env.getProperty("user.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("user.datasource.driver-class-name"));

        return dataSource;
    }

    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan("com.up.tx.manager.login.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("user.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql", env.getProperty("user.jpa.show-sql"));
        properties.put("hibernate.dialect", env.getProperty("user.jpa.database-platform"));

        em.setJpaPropertyMap(properties);

        return em;

    }

    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
