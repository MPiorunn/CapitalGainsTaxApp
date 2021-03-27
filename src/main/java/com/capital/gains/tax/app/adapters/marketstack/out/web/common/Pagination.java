package com.capital.gains.tax.app.adapters.marketstack.out.web.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

    private int limit;
    private int offset;
    private int count;
    private int total;
}
