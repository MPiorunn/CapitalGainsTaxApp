package com.capital.gains.tax.app.core.infrastructure.adapters.inbound.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public void health() {

    }
}
