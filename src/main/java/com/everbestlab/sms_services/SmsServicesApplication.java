package com.everbestlab.sms_services;

import com.everbestlab.sms_services.config.properties.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
		public class SmsServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsServicesApplication.class, args);
	}

	@Primary
	@Bean("defaultRestTemplate")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
