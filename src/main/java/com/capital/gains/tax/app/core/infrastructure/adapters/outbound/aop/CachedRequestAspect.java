package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.aop;

import com.capital.gains.tax.app.commons.UriTokenRemover;
import com.capital.gains.tax.app.core.domain.cache.CachedRequestFacade;
import java.net.URI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
@RequiredArgsConstructor
public class CachedRequestAspect {

    private final CachedRequestFacade cachedRequestFacade;

    @Around("@annotation(request)")
    public Object around(ProceedingJoinPoint pjp, CachedRequest request) throws Throwable {
        String uri = UriTokenRemover.removeToken(getUriFromArgs(pjp.getArgs()));
        Class<?> originalClass = getTypeFromArgs(pjp.getArgs());
        Optional<?> cacheOptional = cachedRequestFacade.getCache(uri);
        if (cacheOptional.isPresent()) {
            log.info("Cache was found for URI {}", uri);
            return Class.forName(originalClass.getName()).cast(cacheOptional.get());
        }
        log.info("No cache was found for URI {} , executing standard request", uri);
        Object proceed = pjp.proceed();
        cachedRequestFacade.saveCache(uri, proceed);
        log.info("Request successful, storing request data in cache");
        return proceed;
    }

    private String getUriFromArgs(Object[] args) {
        return args[0].toString();
    }

    private Class<?> getTypeFromArgs(Object[] args) {
        return (Class<?>) args[1];
    }

}
