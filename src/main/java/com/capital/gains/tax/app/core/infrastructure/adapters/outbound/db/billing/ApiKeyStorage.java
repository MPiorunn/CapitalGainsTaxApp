package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.db.billing;

import com.capital.gains.tax.app.commons.CryptoUtils;
import com.capital.gains.tax.app.core.domain.billing.ApiKey;
import com.capital.gains.tax.app.core.domain.billing.ApiKeyRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyStorage implements ApiKeyRepository {

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
