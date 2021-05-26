package com.capital.gains.tax.app.adapters.tax.in.web;

import com.capital.gains.tax.app.adapters.iex.out.web.DividendTaxResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CapitalGainsTaxController {

    private final CapitalGainsTaxFacade capitalGainsTaxFacade;

    @GetMapping("/cgt/{stock}/{amount}")
    public ResponseEntity<DividendTaxResponse> calculateCapitalGainsTax(@PathVariable String stock,
        @PathVariable String amount) {
        return ResponseEntity.ok(capitalGainsTaxFacade.calculateCapitalGainsTax(stock, Double.parseDouble(amount)));
    }
}
