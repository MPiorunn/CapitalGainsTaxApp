package com.capital.gains.tax.app.adapters.infrastructure;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    public final static String API = "/api";
    public final static String VERSION = "/v1";
    public final static String CURRENCY = API + VERSION + "/currencies";
    public final static String STOCK = API + VERSION + "/stocks";
}