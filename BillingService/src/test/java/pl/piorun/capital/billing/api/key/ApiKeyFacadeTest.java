package pl.piorun.capital.billing.api.key;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.piorun.capital.billing.test.factory.ApiKeyTestFactory;
import pl.piorun.capital.billing.test.factory.UserTestFactory;
import pl.piorun.capital.billing.user.User;
import pl.piorun.capital.billing.user.User.Billing;
import pl.piorun.capital.billing.user.UserRepository;

class ApiKeyFacadeTest {

    ApiKeyRepository apiKeyRepository;
    UserRepository userRepository;
    ApiKeyFacade apiKeyFacade;

    @BeforeEach
    public void setup() {
        apiKeyRepository = Mockito.mock(ApiKeyRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        apiKeyFacade = new ApiKeyFacade(apiKeyRepository, userRepository);
    }

    @Test
    public void shouldReturnFalseWhenApiKeyNotFound() {
        String notPresentId = UUID.randomUUID().toString();
        Mockito.when(apiKeyRepository.findByApiKey(notPresentId)).thenReturn(Optional.empty());

        Assertions.assertFalse(apiKeyFacade.checkIfKeyIsActive(notPresentId));
    }

    @Test
    public void shouldReturnFalseOnInactiveKey() {
        ApiKey inactiveKey = ApiKeyTestFactory.inactiveApiKey();
        Mockito.when(apiKeyRepository.findByApiKey(inactiveKey.getId().toString()))
            .thenReturn(Optional.of(inactiveKey));

        Assertions.assertFalse(apiKeyFacade.checkIfKeyIsActive(inactiveKey.getId().toString()));
    }

    @Test
    public void shouldReturnTrueOnActiveKey() {
        ApiKey activeKey = ApiKeyTestFactory.activeApiKey();
        Mockito.when(apiKeyRepository.findByApiKey(activeKey.getId().toString())).thenReturn(Optional.of(activeKey));

        Assertions.assertTrue(apiKeyFacade.checkIfKeyIsActive(activeKey.getId().toString()));
    }

    @Test
    public void shouldThrowOnInactiveKey() {
        String apiKey = "apiKey";
        ApiKey inactiveApiKey = ApiKeyTestFactory.inactiveApiKey();

        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.of(inactiveApiKey));

        Assertions.assertThrows(IllegalArgumentException.class, () -> apiKeyFacade.applyBilling(apiKey));
    }

    @Test
    public void shouldThrowOnOutdatedKey() {
        String apiKey = "apiKey";
        ApiKey outdatedApiKey = ApiKeyTestFactory.outdatedApiKey();

        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.of(outdatedApiKey));

        Assertions.assertThrows(IllegalArgumentException.class, () -> apiKeyFacade.applyBilling(apiKey));
    }

    @Test
    public void shouldThrowWhenUserNotFound() {
        String apiKey = "apiKey";
        ApiKey activeApiKey = ApiKeyTestFactory.activeApiKey();

        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.of(activeApiKey));
        Mockito.when(userRepository.findByApiKeyId(activeApiKey.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> apiKeyFacade.applyBilling(apiKey));
    }


    @Test
    public void shouldApplyBillingForExistingUserWithActiveApiKey() {
        String apiKey = "apiKey";
        ApiKey activeApiKey = ApiKeyTestFactory.activeApiKey();
        User user = UserTestFactory.createUser(activeApiKey.getId());
        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.of(activeApiKey));
        Mockito.when(userRepository.findByApiKeyId(activeApiKey.getId())).thenReturn(Optional.of(user));

        apiKeyFacade.applyBilling(apiKey);

        Assertions.assertEquals(Billing.STANDARD.getPrice(), user.getMonthlyCost());
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void shouldThrowWhenGeneratingAndUserNotExists() {
        UUID userId = UUID.randomUUID();

        Mockito.when(userRepository.findByApiKeyId(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> apiKeyFacade.generateApiKey(userId));
    }

    @Test
    public void shouldGenerateKeyAndAttachItToUser() {
        User user = UserTestFactory.createUserWithoutKey();
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        String apiKeyValue = apiKeyFacade.generateApiKey(user.getId());

        Mockito.verify(apiKeyRepository).save(Mockito.any(ApiKey.class));
        Mockito.verify(userRepository).save(user);
        Assertions.assertDoesNotThrow(() -> UUID.fromString(apiKeyValue), "Generated Api Key is not an UUID");
    }

    @Test
    public void shouldThrowWhenActivatingNonPresentKey() {
        String apiKey = UUID.randomUUID().toString();
        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> apiKeyFacade.activateApiKey(apiKey));
    }

    @Test
    public void shouldSaveActivatedKey() {
        ApiKey inactiveApiKey = ApiKeyTestFactory.inactiveApiKey();
        String apiKeyValue = inactiveApiKey.getValue();
        Mockito.when(apiKeyRepository.findByApiKey(apiKeyValue)).thenReturn(Optional.of(inactiveApiKey));

        apiKeyFacade.activateApiKey(apiKeyValue);

        Mockito.verify(apiKeyRepository).save(inactiveApiKey);
        Assertions.assertTrue(inactiveApiKey.isActive());
    }

    @Test
    public void shouldThrowWhenDeactivatingNonPresentKey() {
        String apiKey = UUID.randomUUID().toString();
        Mockito.when(apiKeyRepository.findByApiKey(apiKey)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> apiKeyFacade.deactivateApiKey(apiKey));

    }

    @Test
    public void shouldSaveDeactivatedKey() {
        ApiKey active = ApiKeyTestFactory.activeApiKey();
        String apiKeyValue = active.getValue();
        Mockito.when(apiKeyRepository.findByApiKey(apiKeyValue)).thenReturn(Optional.of(active));

        apiKeyFacade.deactivateApiKey(apiKeyValue);

        Mockito.verify(apiKeyRepository).save(active);
        Assertions.assertFalse(active.isActive());
    }

}
