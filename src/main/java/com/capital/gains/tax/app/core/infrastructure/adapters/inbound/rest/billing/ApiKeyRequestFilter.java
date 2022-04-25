package com.capital.gains.tax.app.core.infrastructure.adapters.inbound.rest.billing;

import com.capital.gains.tax.app.core.domain.billing.ApiKeyFacade;
import java.io.IOException;
import java.util.Base64;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class ApiKeyRequestFilter extends OncePerRequestFilter {

    private static final String HEADER_PREFIX = "Basic ";
    private final ApiKeyFacade apiKeyFacade;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURL().toString().contains("cgt");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String apiKey = extractApiKeyFromRequest(request);
        boolean apiKeyActive = apiKeyFacade.checkIfKeyIsActive(apiKey);
        if (!apiKeyActive) {
            throw new IllegalArgumentException("Api key " + apiKey + " is invalid");
        }
        filterChain.doFilter(request, response);
        apiKeyFacade.applyBilling(apiKey);
    }

    private String extractApiKeyFromRequest(HttpServletRequest request) {
        String header = request.getHeader("authorization");
        return decodeHeader(header);
    }

    private static String decodeHeader(String base64Header) {
        if (!StringUtils.hasText(base64Header)) {
            throw new IllegalArgumentException("Api key not provided");
        }
        if (!base64Header.startsWith(HEADER_PREFIX)) {
            throw new IllegalArgumentException("Api key doesn't start with '" + HEADER_PREFIX + "'!");
        }
        return new String(Base64.getDecoder().decode(base64Header.replace(HEADER_PREFIX, ""))).replaceAll(":", "");
    }
}
