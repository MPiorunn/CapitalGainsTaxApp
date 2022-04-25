package com.capital.gains.tax.app.core.infrastructure.adapters.inbound.rest.billing;

import com.capital.gains.tax.app.core.domain.billing.ApiKeyFacade;
import com.capital.gains.tax.app.core.domain.billing.ApiKeyRepository;
import com.capital.gains.tax.app.core.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApiKeyConfig {

    private final UserRepository userRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Bean
    public ApiKeyFacade apiKeyFacade() {
        return new ApiKeyFacade(apiKeyRepository, userRepository);
    }
}
