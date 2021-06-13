package com.capital.gains.tax.app.external.http.iex;

import java.time.LocalDate;
import lombok.Data;

@Data
public class DividendDto {

    private Double amount;
    private String currency;
    private LocalDate declaredDate;
    private LocalDate paymentDate;
    private LocalDate recordDate;
    private String symbol;
}
