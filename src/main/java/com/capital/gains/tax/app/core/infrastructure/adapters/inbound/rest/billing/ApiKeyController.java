package com.capital.gains.tax.app.core.infrastructure.adapters.inbound.rest.billing;

import com.capital.gains.tax.app.core.domain.billing.ApiKeyFacade;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyFacade apiKeyFacade;

    @GetMapping("/apiKeys/generate/{userId}")
    public String generateApiKey(@PathVariable String userId) {
        return apiKeyFacade.generateApiKey(UUID.fromString(userId));
    }

    @PostMapping("/apiKeys/{apiKey}/activate")
    public void activateApiKey(@PathVariable String apiKey) {
        apiKeyFacade.activateApiKey(apiKey);
    }

    @PostMapping("/apiKeys/{apiKey}/deactivate")
    public void deactivateApiKey(@PathVariable String apiKey) {
        apiKeyFacade.deactivateApiKey(apiKey);
    }
}
