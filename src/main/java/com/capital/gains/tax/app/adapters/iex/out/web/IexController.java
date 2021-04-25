package com.capital.gains.tax.app.adapters.iex.out.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IexController {

    private final IexService iexService;

    @GetMapping("/iex")
    public void hehe() {
        iexService.getDividendsFromLastYear("KO");
    }
}
