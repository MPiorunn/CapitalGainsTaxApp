package com.capital.gains.tax.app.core.domain.cache;

import java.net.URI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CachedRequestFacade {

    private final CachedRequestDataRepository cachedRequestDataRepository;

    public Optional<?> getCache(URI uri, Class<?> cacheType) {
        log.debug("Received request for URL {}. Looking for request in cache", uri.toString());
        Optional<CachedRequestData> cacheOptional = cachedRequestDataRepository.findByUri(uri);
        if (cacheOptional.isPresent()) {
            log.debug("Cache was found for URI {}", uri);
            CachedRequestData cachedRequestData = cacheOptional.get();
            return Optional.of(cachedRequestData.getData());
        }
        log.debug("No cache was found for URI {}", uri);
        return Optional.empty();
    }

    public void saveCache(URI uri, Object data) {
        CachedRequestData cachedRequestData = CachedRequestData.of(uri, data.getClass().getName(), data);
        cachedRequestDataRepository.save(cachedRequestData);
    }
}
