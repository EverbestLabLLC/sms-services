package com.everbestlab.sms_services.eskiz.services;

import com.everbestlab.sms_services.config.properties.ApplicationProperties;
import com.everbestlab.sms_services.eskiz.domain.EskizAuthParamsDto;
import com.everbestlab.sms_services.eskiz.domain.EskizAuthResponseDto;
import com.everbestlab.sms_services.eskiz.domain.EskizSmsDto;
import com.everbestlab.sms_services.eskiz.exceptions.EskizServerException;
import com.everbestlab.sms_services.services.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service("eskizSmsService")
public class EskizSmsServiceImpl implements SmsService {


    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    private String getToken() {
        var body = new EskizAuthParamsDto(
                applicationProperties.getEskiz().getEmail(),
                applicationProperties.getEskiz().getPassword()
        );

        var entity = new RequestEntity<>(
                body, getHeaders(), HttpMethod.POST, getAuthTokenURI()
        );

        var response = restTemplate.exchange(entity, EskizAuthResponseDto.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().data().token();
        }
        throw new EskizServerException("Error while getting auth token from Eskiz Server");
    }

    @Override
    public boolean send(String phone, String message) {
        try {

            var request = new RequestEntity<>(
                    buildSendSmsBody(phone, message),
                    getHeaders(getToken()),
                    HttpMethod.POST, getSmsSendURI()
            );
            var response = restTemplate.exchange(request, Object.class);
            log.trace("eskiz sms was sent, response: {}", response);
            return true;
        } catch (RuntimeException e) {
            throw new EskizServerException(e);
        }
    }

    private HttpHeaders getHeaders(String token) {
        var headers = getHeaders();
        headers.setBearerAuth(token);
        return headers;
    }

    private HttpHeaders getHeaders() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private URI getSmsSendURI() {
        return URI.create(applicationProperties.getEskiz().getUrl() + "/message/sms/send");
    }

    private URI getAuthTokenURI() {
        return URI.create(applicationProperties.getEskiz().getUrl() + "/auth/login");
    }

    protected EskizSmsDto buildSendSmsBody(String phone, String message) {
//        message = String.format("%s . %s", message, applicationProperties.getEskiz().getMobileHash());
        return EskizSmsDto.uzSmsBody(phone, message);
    }
}
