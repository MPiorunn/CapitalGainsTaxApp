package app.core.infrastructure.adapters.inbound.rest.billing;

import static app.core.infrastructure.adapters.inbound.rest.billing.ApiKeyExtractor.extractApiKeyFromRequest;

import app.core.domain.billing.BillingInterface;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class BillingFilter extends OncePerRequestFilter {

    private final BillingInterface billingInterface;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURL().toString().contains("cgt");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        UUID apiKey = extractApiKeyFromRequest(request);
        if (!billingInterface.canApplyBilling(apiKey)) {
            throw new IllegalArgumentException("Api key " + apiKey + " is invalid");
        }
        filterChain.doFilter(request, response);
        billingInterface.applyBilling(apiKey);
    }
}
