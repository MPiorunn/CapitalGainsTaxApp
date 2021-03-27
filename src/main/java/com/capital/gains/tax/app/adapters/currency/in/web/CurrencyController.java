package com.capital.gains.tax.app.adapters.currency.in.web;

import com.capital.gains.tax.app.adapters.ApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyFacade currencyFacade;

    @GetMapping(value = ApiConfig.CURRENCY + "/{tradingDate}")
    public ResponseEntity<CurrencyAtDateResponse> getDollarValueFromPreviousDay(@PathVariable String tradingDate) {
        return ResponseEntity.ok(currencyFacade.getPreviousTradingDayPrice(tradingDate));
    }
}

