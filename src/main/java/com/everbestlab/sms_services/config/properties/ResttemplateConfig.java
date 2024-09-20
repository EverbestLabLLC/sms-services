package com.everbestlab.sms_services.config.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ResttemplateConfig {

    @Primary
    @Bean("defaultRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
