package com.capital.gains.tax.app.adapters.iex.out.web;

import com.capital.gains.tax.app.external.http.iex.IexHttpClient;
import com.capital.gains.tax.app.external.http.iex.IexHttpClientImpl;
import org.junit.jupiter.api.Test;

public class IexHttpClientImplTest {


    @Test
    public void dsa(){
        IexHttpClient client = new IexHttpClientImpl(null);
        client.getLastYearDividendsForStock("KO");
    }
}
