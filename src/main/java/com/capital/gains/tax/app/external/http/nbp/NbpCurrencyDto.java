package com.capital.gains.tax.app.external.http.nbp;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NbpCurrencyDto {

    private String table;
    private String currency;
    private String code;
    private List<Rates> rates;

    @Data
    @NoArgsConstructor
    public static class Rates {

        private String no;
        private String effectiveDate;
        private Double bid;
        private Double ask;
    }

}
