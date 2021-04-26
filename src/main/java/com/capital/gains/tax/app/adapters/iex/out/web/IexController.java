package com.capital.gains.tax.app.adapters.iex.out.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IexController {

    private final IexService iexService;

    @GetMapping("/iex/dividend/{stock}/{amount}")
    public ResponseEntity<DividendTaxResponse> getLastYearDividendInfo(@PathVariable String stock,
        @PathVariable String amount) {
        return ResponseEntity.ok(iexService.getTaxForDividendsFromLastYear(stock, Double.parseDouble(amount)));
    }

}
