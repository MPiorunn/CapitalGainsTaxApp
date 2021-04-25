package com.capital.gains.tax.app.adapters.stock.in.web;

import com.capital.gains.tax.app.config.ApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StocksController {

    private final StocksFacade stocksFacade;

    @GetMapping(ApiConfig.STOCK)
    public ResponseEntity<StocksListResponse> getStocksList(@RequestParam String search) {
        return ResponseEntity.ok(stocksFacade.getStocksBySearch(search));
    }
}
