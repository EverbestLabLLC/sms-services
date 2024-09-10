package com.everbestlab.sms_services.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private SayqalProperties sayqal = new SayqalProperties();

}