package pl.piorun.capital.billing.api.key;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import pl.piorun.capital.billing.utils.CryptoUtils;

public interface ApiKeyRepository {

    void save(ApiKey apiKey);

    Optional<ApiKey> findByApiKey(String apiKeyId);

    class ApiKeyRepositoryImpl implements ApiKeyRepository {

        private final Set<ApiKey> apiKeys = new HashSet<>();

        @Override
        public void save(ApiKey apiKey) {
            apiKeys.add(apiKey);
        }

        @Override
        public Optional<ApiKey> findByApiKey(String apiKeyValue) {
            String hash = CryptoUtils.hash(apiKeyValue);
            return apiKeys.stream().filter(apiKey -> apiKey.getValue().equals(hash)).findFirst();
        }
    }
}
