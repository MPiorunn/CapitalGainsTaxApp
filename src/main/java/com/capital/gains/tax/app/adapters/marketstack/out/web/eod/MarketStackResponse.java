package com.capital.gains.tax.app.adapters.marketstack.out.web.eod;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarketStackResponse {

    private Pagination pagination;
    private List<DayOfTrading> data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Pagination {

        private int limit;
        private int offset;
        private int count;
        private int total;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class DayOfTrading {

        private double open;
        private double high;
        private double low;
        private double close;
        private double volume;
        private String symbol;
        private String exchange;
        // @JsonSerialize(using = CustomDateSerializer.class)
        private String date;
    }
}
