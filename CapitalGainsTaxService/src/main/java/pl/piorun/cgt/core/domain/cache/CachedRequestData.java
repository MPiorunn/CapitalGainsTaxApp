package pl.piorun.cgt.core.domain.cache;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CachedRequestData {

    private final UUID id;
    private final String uri;
    private final String className;
    private final Object data;

    public static CachedRequestData of(String uri, String className, Object data) {
        return new CachedRequestData(UUID.randomUUID(), uri, className, data);
    }
}
