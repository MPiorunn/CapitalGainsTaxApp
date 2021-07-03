package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.aop;

import com.capital.gains.tax.app.core.domain.cache.CachedRequestData;
import com.capital.gains.tax.app.core.domain.cache.CachedRequestDataRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CachedDataProvider implements CachedRequestDataRepository {

    private final List<CachedRequestData> cachedRequestData = new ArrayList<>();

    @Override
    public Optional<CachedRequestData> findByUri(URI uri) {
        return cachedRequestData.stream().filter(data -> data.getUri().equals(uri)).findFirst();
    }

    @Override
    public CachedRequestData save(CachedRequestData requestData) {
        cachedRequestData.add(requestData);
        return requestData;
    }
}
