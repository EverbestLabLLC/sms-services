package com.everbestlab.sms_services.services;

import com.everbestlab.sms_services.domain.entities.SmsHistory;
import com.everbestlab.sms_services.repositories.SmsHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsHistoryService {

    private final SmsHistoryRepository smsHistoryRepository;

    @Transactional
    public void save(String phoneNumber) {
        var smsHistory = SmsHistory.builder()
                .phoneNumber(phoneNumber)
                .build();
        smsHistoryRepository.save(smsHistory);
        log.info("Sms History record was created");
    }

}
