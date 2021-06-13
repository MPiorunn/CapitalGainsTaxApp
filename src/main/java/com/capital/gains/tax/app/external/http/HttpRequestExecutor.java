package com.capital.gains.tax.app.external.http;

import java.net.URI;

public interface HttpRequestExecutor {

    <T> T execute(URI requestUrl, Class<T> clazz);
}
