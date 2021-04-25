package com.capital.gains.tax.app.adapters.marketstack.out.web.tickers;

import com.capital.gains.tax.app.adapters.marketstack.out.web.common.MarketStackHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketStackTickersServiceImpl implements MarketStackTickersService {

    private final MarketStackHttpClient marketStackHttpClient;

    @Override
    public MarketStackTickersResponse getCompanies(String search) {
        return marketStackHttpClient.getCompanies(search);
    }
}
