package com.everbestlab.sms_services.services;

import com.everbestlab.sms_services.domain.dto.SmsRequestDto;
import com.everbestlab.sms_services.domain.dto.SmsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsStrategy {

    private final SmsService sayqalSmsService;
    private final SmsService getSmsService;
    private final SmsService eskizSmsService;
    private final SmsHistoryService smsHistoryService;

    public ResponseEntity<SmsResponseDto> sendSms(SmsRequestDto smsRequestDto) {
        var result = false;

        switch (smsRequestDto.provider()) {
            case SAYQAL -> result = sayqalSmsService.send(smsRequestDto.phone(), smsRequestDto.message());
            case GETSMS -> result = getSmsService.send(smsRequestDto.phone(), smsRequestDto.message());
            case ESKIZ -> result = eskizSmsService.send(smsRequestDto.phone(), smsRequestDto.message());
        }

        smsHistoryService.save(smsRequestDto.phone());

        return ResponseEntity.ok().body(new SmsResponseDto(result, null));
    }
}
