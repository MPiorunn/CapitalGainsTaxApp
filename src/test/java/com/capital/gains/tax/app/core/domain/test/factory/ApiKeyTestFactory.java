package com.capital.gains.tax.app.core.domain.test.factory;

import com.capital.gains.tax.app.core.domain.billing.ApiKey;
import java.time.LocalDateTime;
import java.util.UUID;

public class ApiKeyTestFactory {

    public static ApiKey activeApiKey() {
        return generateApiKey(true, LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
    }

    public static ApiKey inactiveApiKey() {
        return generateApiKey(false, LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
    }

    public static ApiKey outdatedApiKey() {
        return generateApiKey(true, LocalDateTime.now().minusMonths(1), LocalDateTime.now().minusDays(1));
    }

    private static ApiKey generateApiKey(boolean active, LocalDateTime createdAt, LocalDateTime validTo) {
        return ApiKey.builder()
            .id(UUID.randomUUID())
            .createdAt(createdAt)
            .validTo(validTo)
            .active(active)
            .build();
    }
}
