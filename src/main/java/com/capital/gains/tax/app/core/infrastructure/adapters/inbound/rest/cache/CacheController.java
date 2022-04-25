package com.capital.gains.tax.app.core.infrastructure.adapters.inbound.rest.cache;


import com.capital.gains.tax.app.core.domain.cache.CachedRequestData;
import com.capital.gains.tax.app.core.domain.cache.CachedRequestDataRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CacheController {

    private final CachedRequestDataRepository cacheRepo;

    @GetMapping("/cache")
    public ResponseEntity<List<CachedRequestData>> getCache() {
        return ResponseEntity.ok(cacheRepo.getAll());
    }

    @DeleteMapping("/cache")
    public void deleteCache() {
        cacheRepo.clearAll();
        log.info("Cache deleted");
    }
}
