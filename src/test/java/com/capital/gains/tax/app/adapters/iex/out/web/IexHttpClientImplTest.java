package com.capital.gains.tax.app.adapters.iex.out.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IexHttpClientImplTest {


    @Test
    public void dsa(){
        IexHttpClient client = new IexHttpClientImpl(null);
        client.getLastYearDividendsForStock("KO");
    }
}
