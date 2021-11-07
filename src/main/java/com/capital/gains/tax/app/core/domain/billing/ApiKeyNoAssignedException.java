package com.capital.gains.tax.app.core.domain.billing;

public class ApiKeyNoAssignedException extends IllegalArgumentException {

    public ApiKeyNoAssignedException(String apiKey) {
        super(String.format("Api key %s is not assigned to any user", apiKey));
    }
}
