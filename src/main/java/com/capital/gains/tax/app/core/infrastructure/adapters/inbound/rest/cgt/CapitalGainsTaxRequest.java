package com.capital.gains.tax.app.core.infrastructure.adapters.inbound.rest.cgt;

import lombok.Data;

@Data
public class CapitalGainsTaxRequest {

    private String stock;
    private Double amount;

}
