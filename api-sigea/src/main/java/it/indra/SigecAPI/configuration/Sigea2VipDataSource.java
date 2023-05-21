package it.indra.SigecAPI.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@SpringBootApplication
@EnableJpaRepositories(
		basePackages = "it.indra.sigea2vip.persistence.repository",
		entityManagerFactoryRef = "sigea2VipEntityManager",
		transactionManagerRef = "sigea2VipTransactionManager")
public class Sigea2VipDataSource {

	@Autowired
	private Environment env;

	@Bean
	@ConfigurationProperties(prefix="spring.oracle-datasource")
	public DataSource sigea2VipDS() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean sigea2VipEntityManager() {
		LocalContainerEntityManagerFactoryBean em
		= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(sigea2VipDS());
		em.setPackagesToScan(
				new String[] { "it.indra.sigea2vip.persistence.entity" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.ddl-auto",
				env.getProperty("spring.oracle-jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect",
				env.getProperty("spring.oracle-jpa.hibernate.dialect"));
		properties.put("hibernate.naming_strategy",
				env.getProperty("spring.oracle-jpa.hibernate.naming_strategy"));
		properties.put("hibernate.naming.physical-strategy",
				env.getProperty("spring.oracle-jpa.hibernate.naming_physical_strategy"));
		em.setJpaPropertyMap(properties);

		return em;
	}


	@Bean
	public PlatformTransactionManager sigea2VipTransactionManager() {

		JpaTransactionManager transactionManager
		= new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(
				sigea2VipEntityManager().getObject());
		return transactionManager;
	}
}
