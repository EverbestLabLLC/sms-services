package com.everbestlab.sms_services.getsms.services;

import com.everbestlab.sms_services.config.properties.ApplicationProperties;
import com.everbestlab.sms_services.getsms.domain.GetSMSBody;
import com.everbestlab.sms_services.services.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service("getSmsService")
public class GetSmsServiceImpl implements SmsService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    @Override
    public boolean send(String phone, String message) {

        try {
            var getSmsProps = applicationProperties.getGetSms();
            var body = GetSMSBody.newInstance(
                    getSmsProps.getLogin(),
                    getSmsProps.getPassword(),
                    phone, message
            );

            var response = restTemplate.postForEntity(getSmsProps.getUrl(), body, String.class);
            log.debug("Sms gateway response: {}", response);
            return true;
        } catch (RuntimeException e) {
            log.error("Error while sending sms by get-sms, {}", e);
            return false;
        }

    }
}
