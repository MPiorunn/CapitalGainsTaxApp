package com.capital.gains.tax.app.adapters.stock.in.web;

import com.capital.gains.tax.app.adapters.marketstack.out.web.common.Pagination;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class StocksListResponse {

    private List<String> stocks;
    private Pagination pagination;
}
