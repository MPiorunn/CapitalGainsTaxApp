package com.capital.gains.tax.app.adapters.marketstack.out.web.tickers;

import com.capital.gains.tax.app.adapters.marketstack.out.web.common.Pagination;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketStackTickersResponse {

    private Pagination pagination;
    private TickerData[] data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TickerData {

        private String name;
        private String symbol;
        private boolean hasIntraday;
        private boolean hasEod;
        private String country;
    }
}
