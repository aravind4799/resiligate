package com.microservices.accounts;

import com.microservices.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableFeignClients
@OpenAPIDefinition(
	info = @Info(
		title = "Accounts Microservice REST API documentation",
		version = "v1",
		contact = @Contact(
			name = "aravind kumar",
			email = "dummy@gmail.com"
		),
       license = @License(
			   name = "Apache 2.0",
		       url = "https://www.apache.org/licenses/LICENSE-2.0"
			)
	)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
