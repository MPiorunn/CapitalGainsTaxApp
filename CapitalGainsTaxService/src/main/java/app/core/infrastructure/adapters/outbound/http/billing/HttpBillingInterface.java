package app.core.infrastructure.adapters.outbound.http.billing;

import app.core.domain.billing.BillingInterface;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HttpBillingInterface implements BillingInterface {

    private final URI billingUri = URI.create("localhost:8081/apiKeys");
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean canApplyBilling(UUID apiKey) {
        BillingDto billingDto = restTemplate.getForObject(billingUri, BillingDto.class);
        if (billingDto == null) {
            return false;
        }
        return billingDto.active;
    }

    @Override
    public void applyBilling(UUID apiKey) {
        restTemplate.postForLocation(billingUri, apiKey);
    }

    private static class BillingDto {

        boolean active;
    }
}
