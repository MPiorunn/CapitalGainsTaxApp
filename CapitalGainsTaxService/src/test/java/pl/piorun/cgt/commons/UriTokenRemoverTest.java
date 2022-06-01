package pl.piorun.cgt.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UriTokenRemoverTest {


    @Test
    public void fullTokenRemoval() {
        String uriWithoutToken = UriTokenRemover
            .removeToken("https://cloud.iexapis.com/v1/stock/AAPL/dividends/1y?token=ojeiuqe918203jdslkj");

        assertEquals("https://cloud.iexapis.com/v1/stock/AAPL/dividends/1y", uriWithoutToken);
    }


    @Test
    public void uriNotChangedWhenNoToken(){
        String uriWithoutToken = UriTokenRemover
            .removeToken("https://cloud.iexapis.com/v1/stock/AAPL/dividends/1y");

        assertEquals("https://cloud.iexapis.com/v1/stock/AAPL/dividends/1y", uriWithoutToken);
    }

}
