package com.capital.gains.tax.app.core.domain.cache;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public interface CachedRequestDataRepository {

    Optional<CachedRequestData> findByUri(String uri);

    void save(CachedRequestData requestData);

    List<CachedRequestData> getAll();

    void clearAll();
}
