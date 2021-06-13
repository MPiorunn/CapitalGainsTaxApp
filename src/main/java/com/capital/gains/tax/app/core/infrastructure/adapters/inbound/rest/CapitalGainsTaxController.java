package com.capital.gains.tax.app.core.infrastructure.adapters.inbound.rest;

import com.capital.gains.tax.app.core.domain.cgt.CapitalGainsTax;
import com.capital.gains.tax.app.core.domain.cgt.CapitalGainsTaxFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CapitalGainsTaxController {

    private final CapitalGainsTaxFacade capitalGainsTaxFacade;

    @GetMapping("/")
    public ResponseEntity<CapitalGainsTax> calculateCapitalGainsTax(CapitalGainsTaxRequest request) {
        return ResponseEntity.ok(capitalGainsTaxFacade.calculateCapitalGainsTax(request.getStock(),request.getAmount()));
    }
}
