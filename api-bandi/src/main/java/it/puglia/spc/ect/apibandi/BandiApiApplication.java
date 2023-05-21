package it.puglia.spc.ect.apibandi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BandiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BandiApiApplication.class, args);
	}

}
