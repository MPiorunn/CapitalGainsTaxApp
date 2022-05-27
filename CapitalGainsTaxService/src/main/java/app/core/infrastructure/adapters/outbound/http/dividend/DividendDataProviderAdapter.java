package app.core.infrastructure.adapters.outbound.http.dividend;

import app.core.domain.dividend.Dividend;
import app.core.domain.dividend.DividendDataProvider;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DividendDataProviderAdapter implements DividendDataProvider {

    private final IexHttpClient iexHttpClient;

    @Override
    public List<Dividend> getLastYearDividends(String stockSymbol) {
        return Arrays.stream(iexHttpClient.getLastYearDividendsForStock(stockSymbol))
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
