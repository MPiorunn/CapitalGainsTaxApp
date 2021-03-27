package com.capital.gains.tax.app.adapters.stock.in.web;

import com.capital.gains.tax.app.adapters.infrastructure.ApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockFacade stockFacade;

    @GetMapping(ApiConfig.STOCK)
    public ResponseEntity<StocksListResponse> getStocksList(@RequestParam String search) {
        return ResponseEntity.ok(stockFacade.getStocksBySearch(search));
    }
}
