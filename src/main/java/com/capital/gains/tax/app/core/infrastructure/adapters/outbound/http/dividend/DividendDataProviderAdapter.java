package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.http.dividend;

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

    /*
    jak zrobić generyczne cache?


    dobrze by było mieć albo abstract klasę albo jakieś proxy albo AOP które

    dodane do zwykłęgo http request executora:
    - może zapisać to co zwraca API w bazie
    - weźmie jako input to co bierze executor
    - będzie na osobnym wątku najlepiej przez jakiś ThreadPool a nie @Async bo Konrad mówił ze to  fhui wolne


    standardowe:
    przychodzi request
    bierzemy parametry i motamy nasz request
    wysyłamy
    odbieramy
    obliczenia
    odpowiedź na request


    z cache:
    przychodzi request
    bierzemy parametry i sprawdzamy czy nie było wcześniej takiego
    jak nie było to :
        bierzemy parametry i motamy nasz request
        wysyłamy
        odbieramy
        zapisujemy w bazie
    jak był to:
        zamiast requesta wyciągamy cache z bazy
    obliczenia
    odpowiedź na request


    to co trzeba będzie niestety zrobić to pewnie ręcznie porobić jakies mongo reposy dla każdego rodzaju odpowiedzi


     */
}
