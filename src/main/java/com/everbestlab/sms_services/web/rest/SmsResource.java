package com.everbestlab.sms_services.web.rest;

import com.everbestlab.sms_services.domain.dto.SmsRequestDto;
import com.everbestlab.sms_services.domain.dto.SmsResponseDto;
import com.everbestlab.sms_services.services.SmsStrategy;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sms")
public class SmsResource {

    private final SmsStrategy smsStrategy;

    @PostMapping("/send")
    public ResponseEntity<SmsResponseDto> sendSms(@RequestBody @Valid SmsRequestDto dto) {
        return smsStrategy.sendSms(dto);
    }

}
