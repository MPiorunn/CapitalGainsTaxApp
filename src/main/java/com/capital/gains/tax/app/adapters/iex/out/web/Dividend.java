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

    /*
    https://cloud.iexapis.com/v1/stock/KO/dividends/1y
        {
        "amount": 0.205,
        "currency": "USD",
        "declaredDate": "2021-01-27",
        "description": "Ordinary Shares",
        "exDate": "2021-02-05",
        "flag": "Cash",
        "frequency": "quarterly",
        "paymentDate": "2021-02-11",
        "recordDate": "2021-02-08",
        "refid": 2148450,
        "symbol": "AAPL",
        "id": "DIVIDENDS",
        "key": "AAPL",
        "subkey": "2148450",
        "date": 1612483200000,
        "updated": 1611837639000
    }
     */
}
