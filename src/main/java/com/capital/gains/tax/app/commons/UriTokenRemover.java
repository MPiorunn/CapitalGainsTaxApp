package com.capital.gains.tax.app.commons;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UriTokenRemover {

    // https://cloud.iexapis.com/v1/stock/AAPL/dividends/1y?token=sk_284ba33aae1e410d956fa5029c6cc909
    // http://api.nbp.pl/api/exchangerates/rates/c/usd/2021-05-12

    private static final List<String> TOKEN_NAMES = List.of("token");
    private static final String NOT_EXISTING_TOKEN = "SomethingThatWillForSureNotOccurInUrl";

    public static String removeToken(String uri) {
        String token = TOKEN_NAMES.stream().filter(uri::contains).findFirst().orElse(NOT_EXISTING_TOKEN);
        String uriWithoutToken = uri.split(token)[0];
        if (uriWithoutToken.endsWith("?")) {
            return uriWithoutToken.replace("?", "");
        }
        return uriWithoutToken;
    }

}
