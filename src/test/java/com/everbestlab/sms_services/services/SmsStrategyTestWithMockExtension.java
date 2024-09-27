package com.everbestlab.sms_services.services;

import com.everbestlab.sms_services.domain.dto.SmsRequestDto;
import com.everbestlab.sms_services.domain.enumeration.SmsProvider;
import com.everbestlab.sms_services.eskiz.services.EskizSmsServiceImpl;
import com.everbestlab.sms_services.getsms.services.GetSmsServiceImpl;
import com.everbestlab.sms_services.sayqal.services.SayqalSmsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SmsStrategyTestWithMockExtension {

    public static final String PHONE = "testPhone";
    public static final String MESSAGE = "test";
    @Mock
    private SayqalSmsServiceImpl sayqalSmsService;
    @Mock
    private GetSmsServiceImpl getSmsService;
    @Mock
    private EskizSmsServiceImpl eskizSmsService;
    @Mock
    private SmsHistoryService smsHistoryService;

    private SmsStrategy cut;

    @BeforeEach
    public void setUp() {
        cut = new SmsStrategy(sayqalSmsService, getSmsService, eskizSmsService, smsHistoryService);
    }

    @Test
    void sendSayqalSmsTest() {

        when(sayqalSmsService.send(anyString(), anyString())).thenReturn(true);
        SmsRequestDto smsRequestDto = new SmsRequestDto(PHONE, MESSAGE, SmsProvider.SAYQAL);
        var responseSayqal = cut.sendSms(smsRequestDto);

        // Assert
        verify(sayqalSmsService).send(PHONE, MESSAGE);
        verify(smsHistoryService).save(PHONE);
        verifyNoInteractions(getSmsService);
        verifyNoInteractions(eskizSmsService);
        assertTrue(responseSayqal.getBody().status());

    }

    @Test
    void sendGetSmsTest() {

        when(getSmsService.send(PHONE, MESSAGE)).thenReturn(true);
        var responseGetSmsWithMock = cut.sendSms(new SmsRequestDto(PHONE, MESSAGE, SmsProvider.GETSMS));

        // Assert
        verify(getSmsService).send(PHONE, MESSAGE);
        verify(smsHistoryService).save(PHONE);
        verifyNoInteractions(sayqalSmsService);
        verifyNoInteractions(eskizSmsService);
        assertTrue(responseGetSmsWithMock.getBody().status());

    }

    @Test
    void sendEskizSmsTest() {

        when(eskizSmsService.send(PHONE, MESSAGE)).thenReturn(true);
        var responseEskizWithMock = cut.sendSms(new SmsRequestDto(PHONE, MESSAGE, SmsProvider.ESKIZ));

        // Assert
        verify(eskizSmsService).send(PHONE, MESSAGE);
        verify(smsHistoryService).save(PHONE);
        verifyNoInteractions(sayqalSmsService);
        verifyNoInteractions(getSmsService);
        assertTrue(responseEskizWithMock.getBody().status());

    }

}
