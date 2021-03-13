package com.capital.gains.tax.app.adapters.out.api.currency;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class NbpCurrencyDto {

    /*
    {"table":"C","currency":"dolar amerykański","code":"USD","rates":[{"no":"064/C/NBP/2016","effectiveDate":"2016-04-04","bid":3.6929,"ask":3.7675}]}
     */
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
