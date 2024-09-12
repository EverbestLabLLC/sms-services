package com.everbestlab.sms_services.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private final SayqalProperties sayqal = new SayqalProperties();
    private final GetSMSProperties getSms = new GetSMSProperties();
    private final EskizProperties eskiz = new EskizProperties();

}