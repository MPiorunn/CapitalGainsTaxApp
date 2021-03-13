package com.capital.gains.tax.app.adapters.in.api.currency;

import com.capital.gains.tax.app.adapters.ApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController(value = ApiConfig.CURRENCY)
public class CurrencyController {

    private final CurrencyFacade currencyFacade;

    @GetMapping("{date}")
    public ResponseEntity<CurrencyAtDateResponse> get(@PathVariable String date) {
        return ResponseEntity.ok(currencyFacade.getCurrencyAtDate(date));
    }
}

