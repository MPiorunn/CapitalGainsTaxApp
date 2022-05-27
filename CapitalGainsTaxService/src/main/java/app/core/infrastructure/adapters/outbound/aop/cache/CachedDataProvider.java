package app.core.infrastructure.adapters.outbound.aop.cache;

import app.core.domain.cache.CachedRequestData;
import app.core.domain.cache.CachedRequestDataRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CachedDataProvider implements CachedRequestDataRepository {

    private final List<CachedRequestData> cachedRequestData = new ArrayList<>();

    @Override
    public Optional<CachedRequestData> findByUri(String uri) {
        return cachedRequestData.stream().filter(data -> data.getUri().equals(uri)).findFirst();
    }

    @Override
    public void save(CachedRequestData requestData) {
        cachedRequestData.add(requestData);
    }

    @Override
    public List<CachedRequestData> getAll() {
        return Collections.unmodifiableList(cachedRequestData);
    }

    @Override
    public void clearAll() {
        cachedRequestData.clear();
    }
}