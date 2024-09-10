package com.everbestlab.sms_services.sayqal.services;

import com.everbestlab.sms_services.config.properties.ApplicationProperties;
import com.everbestlab.sms_services.sayqal.domain.MessageData;
import com.everbestlab.sms_services.sayqal.domain.SayqalTransmitSMS;
import com.everbestlab.sms_services.sayqal.domain.ServiceData;
import com.everbestlab.sms_services.services.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Slf4j
@Primary
@RequiredArgsConstructor
@Service("sayqalSmsService")
public class SayqalSmsServiceImpl implements SmsService {

    public static final String X_ACCESS_TOKEN = "X-Access-Token";

    private final RestTemplate request;
    private final ApplicationProperties applicationProperties;

    @Override
    public boolean send(String phone, String message) {
        try {
            SayqalTransmitSMS body = getSmsBody(phone, message);
            HttpHeaders headers = new HttpHeaders();
            headers.set(X_ACCESS_TOKEN, getMd5());
            HttpEntity<SayqalTransmitSMS> entity = new HttpEntity<>(body, headers);
            ResponseEntity<Object> response = request.postForEntity(applicationProperties.getSayqal().getUrl(), entity, Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Error while sending sms by sayqal, {}", e);
            return false;
        }
    }

    private SayqalTransmitSMS getSmsBody(String phone, String otp) {

        String text = String.format("%s", otp);

        MessageData message = MessageData.builder()
                .phone(phone)
                .smsid(new Random().nextInt(1000000))
                .text(text)
                .build();

        ServiceData service = ServiceData.builder()
                .service(1)
                .build();

        return SayqalTransmitSMS.builder()
                .utime(System.currentTimeMillis() / 1000)
                .username(applicationProperties.getSayqal().getLogin())
                .message(message)
                .service(service)
                .build();
    }

    private String getMd5() {
        String md5 = String.format("TransmitSMS %s %s %d",
                applicationProperties.getSayqal().getLogin(),
                applicationProperties.getSayqal().getPassword(),
                System.currentTimeMillis() / 1000
        );
        return DigestUtils.md5DigestAsHex(md5.getBytes());
    }

}
