package com.capital.gains.tax.app.core.domain.cache;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class CachedRequestFacade {

    private final CachedRequestDataRepository cachedRequestDataRepository;

    public Optional<?> getCache(String uri) {
        log.info("Received request for URL {}. Looking for request in cache", uri);
        Optional<CachedRequestData> cacheOptional = cachedRequestDataRepository.findByUri(uri);
        if (cacheOptional.isPresent()) {
            log.info("Cache was found for URI {}", uri);
            CachedRequestData cachedRequestData = cacheOptional.get();
            return Optional.of(cachedRequestData.getData());
        }
        log.info("No cache was found for URI {}", uri);
        return Optional.empty();
    }

    public void saveCache(String uri, Object data) {
        CachedRequestData cachedRequestData = CachedRequestData.of(uri, data.getClass().getName(), data);
        cachedRequestDataRepository.save(cachedRequestData);
    }
}
