package com.everbestlab.sms_services.eskiz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EskizSmsDto(
        @NotNull @NotBlank @JsonProperty("mobile_phone") String mobilePhone,
        @JsonProperty("country_code") String countryCode,
        String unicode,
        @NotNull @NotBlank String message,
        @JsonProperty("from") String nick
) {

    public static EskizSmsDto uzSmsBody(String phone, String message) {
        return new EskizSmsDto(phone, null, null, message, "4546");
    }

    public static EskizSmsDto globalSmsBody(String phone, String countryCode, String message) {
        return new EskizSmsDto(phone, countryCode, null, message, null);
    }

}
