package com.capital.gains.tax.app.core.domain.test.factory;

import com.capital.gains.tax.app.core.domain.user.User;
import com.capital.gains.tax.app.core.domain.user.User.Billing;
import java.util.UUID;

public class UserTestFactory {

    public static User createUser(UUID apiKeyId) {
        return User.builder()
            .id(UUID.randomUUID())
            .apiKeyId(apiKeyId)
            .billing(Billing.STANDARD)
            .monthlyCost(0.0)
            .build();
    }
}
