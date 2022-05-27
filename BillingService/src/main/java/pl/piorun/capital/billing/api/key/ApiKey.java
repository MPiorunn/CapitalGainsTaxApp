package pl.piorun.capital.billing.api.key;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

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
}
