package app.core.infrastructure.adapters.inbound.rest.cgt;

import lombok.Data;

@Data
class CapitalGainsTaxRequest {

    private String stock;
    private Double amount;

}
