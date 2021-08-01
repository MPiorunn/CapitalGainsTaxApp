package com.capital.gains.tax.app.core.infrastructure.adapters.inbound.rest.cgt;

import com.capital.gains.tax.app.core.domain.cgt.CapitalGainsTax;
import com.capital.gains.tax.app.core.domain.cgt.CapitalGainsTaxFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CapitalGainsTaxController {

    private final CapitalGainsTaxFacade capitalGainsTaxFacade;

    @GetMapping("/")
    public ResponseEntity<CapitalGainsTax> calculateCapitalGainsTax(CapitalGainsTaxRequest request) {
        log.info("Received cgt request for {} of {}" ,request.getAmount(),request.getStock());
        return ResponseEntity.ok(capitalGainsTaxFacade.calculateCapitalGainsTax(request.getStock(),request.getAmount()));
    }
}
