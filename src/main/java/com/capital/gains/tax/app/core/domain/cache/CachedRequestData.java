package com.capital.gains.tax.app.core.domain.cache;

import java.net.URI;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CachedRequestData {

    private final UUID id;
    private final URI uri;
    private final String className;
    private final Object data;

    public static CachedRequestData of(URI uri, String className, Object data) {
        return new CachedRequestData(UUID.randomUUID(), uri, className, data);
    }
}
