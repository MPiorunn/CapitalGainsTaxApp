package pl.piorun.billing.test.factory;

import java.util.UUID;
import pl.piorun.billing.user.User;
import pl.piorun.billing.user.User.Billing;

public class UserTestFactory {

    public static User createUserWithoutKey() {
        return User.builder()
            .id(UUID.randomUUID())
            .apiKeyId(null)
            .billing(Billing.STANDARD)
            .monthlyCost(0.0)
            .build();
    }

    public static User createUser(UUID apiKeyId) {
        return User.builder()
            .id(UUID.randomUUID())
            .apiKeyId(apiKeyId)
            .billing(Billing.STANDARD)
            .monthlyCost(0.0)
            .build();
    }
}
