package com.everbestlab.sms_services.eskiz.domain;

public record EskizAuthResponseDto(
        String message,
        EskizTokenDTO data,
        String tokenType
) {
    public record EskizTokenDTO(String token){}
}
