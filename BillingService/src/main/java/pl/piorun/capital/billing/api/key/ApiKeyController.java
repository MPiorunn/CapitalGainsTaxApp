package pl.piorun.capital.billing.api.key;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyFacade apiKeyFacade;

    @PostMapping("/apiKeys")
    public String generateApiKey(@RequestBody String userId) {
        return apiKeyFacade.generateApiKey(UUID.fromString(userId));
    }

    @GetMapping("/apiKeys/{apiKey}/active")
    public boolean isKeyValid(@PathVariable String apiKey) {
        return apiKeyFacade.checkIfKeyIsActive(apiKey);
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
