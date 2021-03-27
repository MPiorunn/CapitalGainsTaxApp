package com.capital.gains.tax.app.adapters.marketstack.out.web.common;

import com.capital.gains.tax.app.adapters.infrastructure.HttpRequestExecutor;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketStackRequestExecutor implements HttpRequestExecutor {

    private final String ACCESS_KEY;
    private final HttpRequestExecutor httpRequestExecutor;

    @Override
    public <T> T execute(String requestUrl, Class<T> clazz, Map<String, String> variables) {
        variables.put("access_key", "f9aa1b86bd497bc47e1161fe9c404015");
        return httpRequestExecutor.execute(requestUrl, clazz, variables);
    }
}
