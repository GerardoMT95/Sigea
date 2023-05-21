package it.indra.SigecAPI.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import it.indra.SigecAPI.util.WrapperFilterRepositoryImpl;

@Configuration
@EnableJpaRepositories(
		basePackages = {"it.indra.SigecAPI"}, 
		repositoryBaseClass = WrapperFilterRepositoryImpl.class,
		entityManagerFactoryRef = "sigecapiEntityManager", 
		transactionManagerRef = "sigecapiTransactionManager"
		)
public class SigecapiDataSource {

	@Autowired
	private Environment env;

	@Primary
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource sigecapiDS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean sigecapiEntityManager() {
		LocalContainerEntityManagerFactoryBean em
		= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(sigecapiDS());
		em.setPackagesToScan(
				new String[] { "it.indra.SigecAPI" });

		HibernateJpaVendorAdapter vendorAdapter
		= new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect",
				env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.ddl-auto",
				env.getProperty("spring.jpa.generate-ddl"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Primary
	@Bean
	public PlatformTransactionManager sigecapiTransactionManager() {

		JpaTransactionManager transactionManager
		= new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(
				sigecapiEntityManager().getObject());
		return transactionManager;
	}
}
