package com.capital.gains.tax.app.core.domain.cache;

import java.net.URI;
import java.util.Optional;

public interface CachedRequestDataRepository {

    Optional<CachedRequestData> findByUri(URI uri);

    CachedRequestData save(CachedRequestData requestData);

}
