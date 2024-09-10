package com.everbestlab.sms_services.getsms.domain;

import com.everbestlab.sms_services.utils.JsonUtil;

import java.util.List;

public record GetSMSBody(
        String login,
        String password,
        String data
) {

    public static GetSMSBody newInstance(String login, String password, String phone, String text) {
        var data = new Data(phone, text);
        return new GetSMSBody(login, password, JsonUtil.toJson(List.of(data)));
    }

    public record Data (
            String phone,
            String text
    ) {}

}
