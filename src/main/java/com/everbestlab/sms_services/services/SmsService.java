package com.everbestlab.sms_services.services;

public interface SmsService {

    boolean send(String phone, String message);

}
