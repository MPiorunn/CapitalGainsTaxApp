package app.core.domain.billing;

import java.util.UUID;

public interface BillingInterface {

    boolean canApplyBilling(UUID apiKey);

    void applyBilling(UUID apiKey);
}
