package com.capital.gains.tax.app.adapters.nbp.out.web;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class NbpCurrencyDto {

    private String table;
    private String currency;
    private String code;
    private List<Rates> rates;

    @Data
    @NoArgsConstructor
    static class Rates {

        private String no;
        private String effectiveDate;
        private Double bid;
        private Double ask;
    }

}
