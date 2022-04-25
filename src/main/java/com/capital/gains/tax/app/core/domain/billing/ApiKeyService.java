package com.capital.gains.tax.app.core.domain.billing;

import com.capital.gains.tax.app.commons.CryptoUtils;
import com.capital.gains.tax.app.core.domain.user.User;
import com.capital.gains.tax.app.core.domain.user.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final UserRepository userRepository;

    public boolean checkIfKeyIsActive(String apiKey) {
        Optional<ApiKey> storedApiKey = apiKeyRepository.findByApiKey(apiKey);

        if (storedApiKey.isEmpty()) {
            return false;
        }
        return storedApiKey.get().isActive();
    }

    public void applyBilling(String apiKey) {
        ApiKey storedApiKey = apiKeyRepository.findByApiKey(apiKey)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Api key %s was not found!", apiKey)));
        if (!storedApiKey.isActive()) {
            throw new IllegalArgumentException("Api key inactive!");
        }

        User user = userRepository.findByApiKeyId(storedApiKey.getId())
            .orElseThrow(
                () -> new IllegalArgumentException(String.format("Api key %s is not assigned to any user", apiKey)));

        user.requestMade();
        userRepository.save(user);
    }

    public String generateApiKey(UUID userId) {

        User user = userRepository.findById(userId).orElseThrow();

        String apiKeyValue = UUID.randomUUID().toString();
        ApiKey apiKey = ApiKey.builder()
            .id(UUID.randomUUID())
            .value(CryptoUtils.hash(apiKeyValue))
            .createdAt(LocalDateTime.now())
            .validTo(LocalDateTime.now().plusMonths(1))
            .active(false)
            .build();

        apiKeyRepository.save(apiKey);

        user.addApiKey(apiKey.getId());
        userRepository.save(user);

        return apiKeyValue;
    }

    public void activateApiKey(String apiKey) {
        ApiKey storedApiKey = apiKeyRepository.findByApiKey(apiKey)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Api key %s was not found!", apiKey)));

        storedApiKey.activate();
        apiKeyRepository.save(storedApiKey);
    }


    public void deactivateApiKey(String apiKey) {
        ApiKey storedApiKey = apiKeyRepository.findByApiKey(apiKey)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Api key %s was not found!", apiKey)));

        storedApiKey.deactivate();
        apiKeyRepository.save(storedApiKey);
    }
}
