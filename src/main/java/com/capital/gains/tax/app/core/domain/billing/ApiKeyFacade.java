package com.capital.gains.tax.app.core.domain.billing;

import com.capital.gains.tax.app.core.domain.user.UserRepository;
import java.util.UUID;

public class ApiKeyFacade {

    private final ApiKeyService apiKeyService;

    public ApiKeyFacade(ApiKeyRepository apiKeyRepository, UserRepository userRepository) {
        this.apiKeyService = new ApiKeyService(apiKeyRepository, userRepository);
    }

    public boolean checkIfKeyIsActive(String apiKey) {
        return apiKeyService.checkIfKeyIsActive(apiKey);
    }

    public void applyBilling(String apiKey) {
        apiKeyService.applyBilling(apiKey);
    }

    public String generateApiKey(UUID userId) {
        return apiKeyService.generateApiKey(userId);
    }

    public void activateApiKey(String apiKey) {
        apiKeyService.activateApiKey(apiKey);
    }

    public void deactivateApiKey(String apiKey) {
        apiKeyService.deactivateApiKey(apiKey);
    }
}
