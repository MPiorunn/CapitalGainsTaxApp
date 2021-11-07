package com.capital.gains.tax.app.core.domain.billing;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class ApiKey {

    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime validTo;
    private String value;
    private boolean active;

    void activate() {
        this.active = true;
    }

    void deactivate() {
        this.active = false;
    }
}
