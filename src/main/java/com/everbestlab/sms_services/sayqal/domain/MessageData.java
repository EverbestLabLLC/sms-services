package com.everbestlab.sms_services.sayqal.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageData {
    private int smsid;
    private String phone;
    private String text;

}
