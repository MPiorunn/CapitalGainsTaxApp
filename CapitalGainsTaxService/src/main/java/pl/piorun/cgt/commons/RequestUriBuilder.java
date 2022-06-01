package pl.piorun.cgt.commons;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class RequestUriBuilder {

    private final Map<String, String> pathVariables = new HashMap<>();
    private final MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
    private String baseUrl;

    public static RequestUriBuilder builder() {
        return new RequestUriBuilder();
    }

    public RequestUriBuilder fromUrl(String url) {
        this.baseUrl = url;
        return this;
    }

    public RequestUriBuilder pathVariable(String key, String value) {
        pathVariables.put(key, value);
        return this;
    }

    public RequestUriBuilder queryParam(String key, String value) {
        queryParameters.add(key, value);
        return this;
    }

    public URI build() {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
            .queryParams(queryParameters)
            .buildAndExpand(pathVariables)
            .toUri();
    }
}
