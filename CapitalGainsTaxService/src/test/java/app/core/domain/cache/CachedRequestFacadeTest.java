package app.core.domain.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CachedRequestFacadeTest {

    private CachedRequestDataRepository cachedRequestDataRepository;

    private CachedRequestFacade facade;

    @BeforeEach
    public void setup() {
        cachedRequestDataRepository = Mockito.mock(CachedRequestDataRepository.class);
        facade = new CachedRequestFacade(cachedRequestDataRepository);
    }

    @Test
    public void shouldReturnEmptyResponseWhenCacheNotFound() {
        //given
        String uri = "someUri";
        Mockito.when(cachedRequestDataRepository.findByUri(uri)).thenReturn(Optional.empty());

        //when
        Optional<?> cache = facade.getCache(uri);

        //then
        assertTrue(cache.isEmpty());
    }


    @Test
    public void shouldReturnCacheWhenWasPreviouslyStored() {
        //given
        String uri = "someUri";
        String data = "cached data";
        CachedRequestData cachedRequestData = CachedRequestData.of(uri, "String", data);
        Mockito.when(cachedRequestDataRepository.findByUri(uri)).thenReturn(Optional.of(cachedRequestData));

        //when
        Optional<?> cache = facade.getCache(uri);

        //then
        assertTrue(cache.isPresent());
        String cachedData = (String) cache.get();
        assertEquals(data, cachedData);
    }

    @Test
    public void shouldSaveAnyDataToCache() {
        //given
        String uri = "some uri";
        Object data = new Object();

        //when
        facade.saveCache(uri, data);

        //then
        cachedRequestDataRepository.save(Mockito.any(CachedRequestData.class));
    }
}
