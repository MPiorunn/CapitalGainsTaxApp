package pl.piorun.billing.api.key;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import pl.piorun.billing.utils.CryptoUtils;

@Builder
public class ApiKey {

    @Getter
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime validTo;
    @Getter
    private String value;
    private boolean active;

    boolean isActive() {
        return active && validTo.isAfter(LocalDateTime.now());
    }

    void activate() {
        this.active = true;
    }

    void deactivate() {
        this.active = false;
    }

    public static ApiKey generateFresh(String apiKeyValue) {
        return ApiKey.builder()
            .id(UUID.randomUUID())
            .value(CryptoUtils.hash(apiKeyValue))
            .createdAt(LocalDateTime.now())
            .validTo(LocalDateTime.now().plusMonths(1))
            .active(false)
            .build();
    }
}
