package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.aop;

import com.capital.gains.tax.app.core.domain.cache.CachedRequestFacade;
import java.net.URI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class CachedRequestAspect {

    private final CachedRequestFacade cachedRequestFacade;

    @Around("@annotation(request)")
    public Object around(ProceedingJoinPoint pjp, CachedRequest request) throws Throwable {
        URI uri = getUriFromArgs(pjp.getArgs());
        Class<?> originalClass = getTypeFromArgs(pjp.getArgs());
        Optional<?> cacheOptional = cachedRequestFacade.getCache(uri, originalClass);
        if (cacheOptional.isPresent()) {
            log.debug("Cache was found for URI {}", uri);
            return Class.forName(originalClass.getName()).cast(cacheOptional.get());
        }
        log.debug("No cache was found for URI {} , executing standard request", uri);
        Object proceed = pjp.proceed();
        cachedRequestFacade.saveCache(uri, proceed);
        log.debug("Request successful, storing request data in cache");
        return proceed;
    }

    private URI getUriFromArgs(Object[] args) {
        return (URI) args[0];
    }

    private Class<?> getTypeFromArgs(Object[] args) {
        return (Class<?>) args[1];
    }

}
