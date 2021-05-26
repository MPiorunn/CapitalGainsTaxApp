package com.capital.gains.tax.app.adapters.iex.out.web;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Dividend {

    private Double amount;
    private String currency;
    private LocalDate declaredDate;
    private LocalDate paymentDate;
    private LocalDate recordDate;
    private String symbol;
}
