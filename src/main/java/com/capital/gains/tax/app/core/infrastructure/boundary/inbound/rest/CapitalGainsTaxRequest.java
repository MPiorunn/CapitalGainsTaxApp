package com.capital.gains.tax.app.core.infrastructure.boundary.inbound.rest;

import lombok.Data;

@Data
public class CapitalGainsTaxRequest {

    private String stock;
    private Double amount;

}
