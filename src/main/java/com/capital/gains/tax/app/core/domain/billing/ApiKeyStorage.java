package com.capital.gains.tax.app.core.domain.billing;

import java.util.Optional;

interface ApiKeyStorage {

    ApiKey save(ApiKey apiKey);

    Optional<ApiKey> findByApiKey(String apiKeyId);
}
