package app.commons;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UriTokenRemover {

    private static final List<String> TOKEN_NAMES = List.of("token");
    private static final String NOT_EXISTING_TOKEN = "SomethingThatWillForSureNotOccurInUrl";

    public static String removeToken(String uri) {
        String token = TOKEN_NAMES.stream().filter(uri::contains).findFirst().orElse(NOT_EXISTING_TOKEN);
        String uriWithoutToken = uri.split(token)[0];
        if (uriWithoutToken.endsWith("?")) {
            return uriWithoutToken.replace("?", "");
        }
        return uriWithoutToken;
    }

}
