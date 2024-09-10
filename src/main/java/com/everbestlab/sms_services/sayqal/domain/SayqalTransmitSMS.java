package com.everbestlab.sms_services.sayqal.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SayqalTransmitSMS {
    private long utime;
    private String username;
    private ServiceData service;
    private MessageData message;
}
