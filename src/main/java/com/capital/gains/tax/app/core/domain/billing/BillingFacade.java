package com.capital.gains.tax.app.core.domain.billing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BillingFacade {

    private final ApiKeyStorage apiKeyStorage;

    private List<User> users = List.of();

    public void applyBilling(String apiKey) {
        String hashedKey = apiKey.toUpperCase(Locale.ROOT);//XD
        ApiKey storedApiKey = apiKeyStorage.findByApiKey(hashedKey)
            .orElseThrow(() -> new ApiKeyNotFoundException(apiKey));

        User user = users.stream()
            .filter(u -> u.getApiKeyId().equals(storedApiKey.getId()))
            .findFirst()
            .orElseThrow(() -> new ApiKeyNoAssignedException(apiKey));

        user.requestMade();

    }

    public String generateApiKey() {
        String apiKeyValue = UUID.randomUUID().toString();

        ApiKey apiKey = ApiKey.builder()
            .id(UUID.randomUUID())
            // todo hash
            .value(apiKeyValue.toUpperCase(Locale.ROOT))
            .createdAt(LocalDateTime.now())
            .validTo(LocalDateTime.now().plusMonths(1))
            .active(false)
            .build();

        apiKeyStorage.save(apiKey);

        return apiKeyValue;
    }


    private void activateApiKey(String apiKey) {
        ApiKey storedApiKey = apiKeyStorage.findByApiKey(apiKey.toUpperCase(Locale.ROOT))
            .orElseThrow(() -> new ApiKeyNotFoundException(apiKey));

        storedApiKey.activate();
        apiKeyStorage.save(storedApiKey);
    }


    public void deactivateApiKey(String apiKey) {

        ApiKey storedApiKey = apiKeyStorage.findByApiKey(apiKey.toUpperCase(Locale.ROOT))
            .orElseThrow(() -> new ApiKeyNotFoundException(apiKey));

        storedApiKey.deactivate();
        apiKeyStorage.save(storedApiKey);
    }
}
