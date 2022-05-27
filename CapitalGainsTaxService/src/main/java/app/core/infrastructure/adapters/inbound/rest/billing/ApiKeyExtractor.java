package app.core.infrastructure.adapters.inbound.rest.billing;

import java.util.Base64;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiKeyExtractor {

    private static final String HEADER_PREFIX = "Basic ";

    public static UUID extractApiKeyFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("authorization");
        String apiKey = decodeAuthHeader(authHeader);
        return UUID.fromString(apiKey);
    }

    private static String decodeAuthHeader(String base64Header) {
        if (!StringUtils.hasText(base64Header)) {
            throw new IllegalArgumentException("Api key not provided");
        }
        if (!base64Header.startsWith(HEADER_PREFIX)) {
            throw new IllegalArgumentException("Api key doesn't start with '" + HEADER_PREFIX + "'!");
        }
        return new String(Base64.getDecoder().decode(base64Header.replace(HEADER_PREFIX, ""))).replaceAll(":", "");
    }
}
