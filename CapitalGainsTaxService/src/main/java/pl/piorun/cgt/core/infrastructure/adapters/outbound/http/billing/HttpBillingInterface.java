package pl.piorun.cgt.core.infrastructure.adapters.outbound.http.billing;

import pl.piorun.cgt.core.domain.billing.BillingInterface;
import java.net.URI;
import java.util.UUID;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class HttpBillingInterface implements BillingInterface {

    private static final String BASE_BILLING_URL = "http://localhost:8081/apiKeys";
    private static final String IS_KEY_ACTIVE_URL = BASE_BILLING_URL + "/{apiKey}/active";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean canApplyBilling(UUID apiKey) {
        URI billingUri = UriComponentsBuilder.fromHttpUrl(IS_KEY_ACTIVE_URL)
            .buildAndExpand(apiKey)
            .toUri();
        BillingDto billingDto = restTemplate.getForObject(billingUri, BillingDto.class);
        if (billingDto == null) {
            return false;
        }
        return billingDto.active;
    }

    @Override
    public void applyBilling(UUID apiKey) {
        System.out.println("POSTREEE HEEHEHEH");
//        restTemplate.postForLocation(billingUri, apiKey);
    }

    @Data
    private static class BillingDto {

        final boolean active;
    }
}
