package pl.piorun.cgt.core.domain.cache;

import java.util.List;
import java.util.Optional;

public interface CachedRequestDataRepository {

    Optional<CachedRequestData> findByUri(String uri);

    void save(CachedRequestData requestData);

    List<CachedRequestData> getAll();

    void clearAll();
}
