package com.capital.gains.tax.app.core.domain.billing;

import com.capital.gains.tax.app.core.domain.test.factory.ApiKeyTestFactory;
import com.capital.gains.tax.app.core.domain.test.factory.UserTestFactory;
import com.capital.gains.tax.app.core.domain.user.User;
import com.capital.gains.tax.app.core.domain.user.User.Billing;
import com.capital.gains.tax.app.core.domain.user.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ApiKeyServiceTest {


    ApiKeyRepository apiKeyRepository;
    UserRepository userRepository;
    ApiKeyService keyService;

    @BeforeEach
    public void setup() {
        apiKeyRepository = Mockito.mock(ApiKeyRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        keyService = new ApiKeyService(apiKeyRepository, userRepository);
    }


    @Test
    public void shouldThrowExceptionOnInactiveKey() {
        String apiKey = "apiKey";
        ApiKey inactiveApiKey = ApiKeyTestFactory.inactiveApiKey();

        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.of(inactiveApiKey));

        Assertions.assertThrows(IllegalArgumentException.class, () -> keyService.applyBilling(apiKey));
    }

    @Test
    public void shouldThrowExceptionOnOutdatedKey() {
        String apiKey = "apiKey";
        ApiKey outdatedApiKey = ApiKeyTestFactory.outdatedApiKey();

        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.of(outdatedApiKey));

        Assertions.assertThrows(IllegalArgumentException.class, () -> keyService.applyBilling(apiKey));
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        String apiKey = "apiKey";
        ApiKey activeApiKey = ApiKeyTestFactory.activeApiKey();

        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.of(activeApiKey));
        Mockito.when(userRepository.findByApiKeyId(activeApiKey.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> keyService.applyBilling(apiKey));
    }


    @Test
    public void shouldApplyBillingForExistingUserWithActiveApiKey() {
        String apiKey = "apiKey";
        ApiKey activeApiKey = ApiKeyTestFactory.activeApiKey();
        User user = UserTestFactory.createUser(activeApiKey.getId());

        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.of(activeApiKey));
        Mockito.when(userRepository.findByApiKeyId(activeApiKey.getId())).thenReturn(Optional.of(user));

        Assertions.assertEquals(Billing.STANDARD.getPrice(), user.getMonthlyCost());
    }

}
