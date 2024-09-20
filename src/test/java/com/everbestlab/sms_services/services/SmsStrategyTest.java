package com.everbestlab.sms_services.services;

import com.everbestlab.sms_services.domain.dto.SmsRequestDto;
import com.everbestlab.sms_services.domain.enumeration.SmsProvider;
import com.everbestlab.sms_services.sayqal.services.SayqalSmsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmsStrategyTest {

    public static final String PHONE = "testPhone";
    public static final String MESSAGE = "test";
    @Mock
    private SmsService sayqalSmsService;
    @Mock
    private SmsService getSmsService;
    @Mock
    private SmsService eskizSmsService;
    @Mock
    private SmsHistoryService smsHistoryService;

    @InjectMocks
    private SmsStrategy cut;

    private AutoCloseable openMocks;

    @BeforeEach
    void setupMocks() {
        this.openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendSmsTest() {

        sayqalSmsService = Mockito.mock(SayqalSmsServiceImpl.class);
        getSmsService = Mockito.mock(SayqalSmsServiceImpl.class);
        eskizSmsService = Mockito.mock(SayqalSmsServiceImpl.class);
        smsHistoryService = Mockito.mock(SmsHistoryService.class);

        cut = new SmsStrategy(sayqalSmsService, getSmsService, eskizSmsService, smsHistoryService);

        Mockito.when(sayqalSmsService.send(PHONE, MESSAGE)).thenReturn(true);

        var responseSayqal = cut.sendSms(new SmsRequestDto(PHONE, MESSAGE, SmsProvider.SAYQAL));
        assertTrue(responseSayqal.getBody().status());

        var responseGetSms = cut.sendSms(new SmsRequestDto(PHONE, MESSAGE, SmsProvider.GETSMS));
        assertFalse(responseGetSms.getBody().status());

        var responseEskiz = cut.sendSms(new SmsRequestDto(PHONE, MESSAGE, SmsProvider.ESKIZ));
        assertFalse(responseEskiz.getBody().status());

        Mockito.when(getSmsService.send(PHONE, MESSAGE)).thenReturn(true);
        Mockito.when(eskizSmsService.send(PHONE, MESSAGE)).thenReturn(true);

        var responseGetSmsWithMock = cut.sendSms(new SmsRequestDto(PHONE, MESSAGE, SmsProvider.GETSMS));
        assertTrue(responseGetSmsWithMock.getBody().status());

        var responseEskizWithMock = cut.sendSms(new SmsRequestDto(PHONE, MESSAGE, SmsProvider.ESKIZ));
        assertTrue(responseEskizWithMock.getBody().status());

    }

}
