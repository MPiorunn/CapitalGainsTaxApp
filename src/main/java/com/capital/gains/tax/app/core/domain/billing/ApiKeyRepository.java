package com.capital.gains.tax.app.core.domain.billing;

import java.util.Optional;

public interface ApiKeyRepository {

    void save(ApiKey apiKey);

    Optional<ApiKey> findByApiKey(String apiKeyId);
}
