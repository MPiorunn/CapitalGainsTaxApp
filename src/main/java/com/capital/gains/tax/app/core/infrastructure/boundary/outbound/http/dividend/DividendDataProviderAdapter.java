package com.capital.gains.tax.app.core.infrastructure.boundary.outbound.http.dividend;

import com.capital.gains.tax.app.external.http.iex.DividendDto;
import com.capital.gains.tax.app.external.http.iex.IexHttpClient;
import com.capital.gains.tax.app.core.domain.dividend.Dividend;
import com.capital.gains.tax.app.core.domain.dividend.DividendDataProvider;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DividendDataProviderAdapter implements DividendDataProvider {

    private final IexHttpClient httpClient;

    @Override
    public List<Dividend> getLastYearDividends(String stockSymbol) {
        return Arrays.stream(httpClient.getLastYearDividendsForStock(stockSymbol))
            .map(this::map)
            .collect(Collectors.toList());
    }

    private Dividend map(DividendDto dto) {
        return Dividend.builder()
            .amount(dto.getAmount())
            .declaredDate(dto.getDeclaredDate())
            .paymentDate(dto.getPaymentDate())
            .recordDate(dto.getRecordDate())
            .build();
    }
}
