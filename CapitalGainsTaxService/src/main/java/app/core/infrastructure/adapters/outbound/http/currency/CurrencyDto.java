package app.core.infrastructure.adapters.outbound.http.currency;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyDto {

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
