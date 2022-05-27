package pl.piorun.capital.billing.test.factory;

import java.util.UUID;
import pl.piorun.capital.billing.user.User;
import pl.piorun.capital.billing.user.User.Billing;

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
