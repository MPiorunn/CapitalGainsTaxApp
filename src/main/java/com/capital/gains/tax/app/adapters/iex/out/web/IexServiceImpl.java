package com.capital.gains.tax.app.adapters.iex.out.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IexServiceImpl implements IexService {

    private final IexHttpClient httpClient;

    @Override
    public List<Dividend> getDividendsFromLastYear(String symbol) {
        return httpClient.getLastYearDividendsForStock(symbol);
    }
}
