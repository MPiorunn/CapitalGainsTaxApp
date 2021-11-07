package com.capital.gains.tax.app.core.domain.billing;

public class ApiKeyNotFoundException extends IllegalArgumentException {

    public ApiKeyNotFoundException(String apiKey) {
        super(String.format("Api key %s was not found!", apiKey));
    }
}
