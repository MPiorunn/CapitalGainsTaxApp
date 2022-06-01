package pl.piorun.cgt.core.infrastructure.adapters.inbound.rest.cgt;

import pl.piorun.cgt.core.domain.cgt.CapitalGainsTax;
import pl.piorun.cgt.core.domain.cgt.CapitalGainsTaxFacade;
import pl.piorun.cgt.core.infrastructure.adapters.outbound.http.currency.NbpHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CapitalGainsTaxController {

    private final CapitalGainsTaxFacade capitalGainsTaxFacade;

    private final NbpHttpClient nbpHttpClient;


    @GetMapping("/cgt")
    public CapitalGainsTax calculateCapitalGainsTax(CapitalGainsTaxRequest request) {
        log.info("Received CGT request for {} of {}", request.getAmount(), request.getStock());
        return capitalGainsTaxFacade.calculateCapitalGainsTax(request.getStock(), request.getAmount());
    }

//
//    @GetMapping("/money")
//    public void getAllDaysPlx() throws IOException {
//        Map<LocalDate, Double> prices = new TreeMap<>();
//        LocalDate today = LocalDate.of(2019, 12, 30);
//        try {
//            for (int i = 0; i < 3660; i++) {
//                LocalDate date = today.minusDays(i);
//                LocalDate previousTradingDay = DateUtils.getPreviousTradingDay(date);
//                CurrencyDto currencyDto;
//                try {
//                    currencyDto = nbpHttpClient.getCurrencyAtTradingDay(previousTradingDay);
//                } catch (HttpClientErrorException e) {
//                    try {
//                        previousTradingDay = DateUtils.getPreviousTradingDay(previousTradingDay);
//                        currencyDto = nbpHttpClient.getCurrencyAtTradingDay(previousTradingDay);
//                    } catch (HttpClientErrorException ex) {
//                        previousTradingDay = DateUtils.getPreviousTradingDay(previousTradingDay);
//                        currencyDto = nbpHttpClient.getCurrencyAtTradingDay(previousTradingDay);
//                    }
//                }
//                Double dollar = currencyDto.getRates().get(0).getAsk();
//                prices.put(previousTradingDay, dollar);
//            }
//        } catch (Exception e) {
//            System.out.println("Poleciał exception tak czy siak ");
//            e.printStackTrace();
//        }
//        BufferedWriter bf = new BufferedWriter(new FileWriter("prices.csv"));
//
//        for (Entry<LocalDate, Double> price : prices.entrySet()) {
//            bf.write(price.getKey() + "," + price.getValue() + "\n");
//        }
//
//        bf.close();
//
//    }


}
