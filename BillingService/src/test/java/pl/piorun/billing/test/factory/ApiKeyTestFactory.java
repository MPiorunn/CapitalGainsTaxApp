package pl.piorun.billing.test.factory;

import java.time.LocalDateTime;
import java.util.UUID;
import pl.piorun.billing.api.key.ApiKey;

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
