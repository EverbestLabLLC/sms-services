package com.everbestlab.sms_services.domain.dto;

import com.everbestlab.sms_services.domain.enumeration.SmsProvider;
import jakarta.validation.constraints.NotNull;

public record SmsRequestDto(@NotNull String phone,
                            @NotNull String message,
                            @NotNull SmsProvider provider) {
}
