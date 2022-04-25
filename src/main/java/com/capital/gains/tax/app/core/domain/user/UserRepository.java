package com.capital.gains.tax.app.core.domain.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findByApiKeyId(UUID apiKeyId);

    Optional<User> findById(UUID userId);

    void save(User user);
}
