package app.adapters.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import app.commons.RequestUriBuilder;
import java.net.URI;
import org.junit.jupiter.api.Test;

public class RequestUriBuilderTest {

    @Test
    public void shouldThrowExceptionOnEmptyBuilder() {
        assertThrows(
            IllegalArgumentException.class,
            () -> RequestUriBuilder.builder().build()
        );
    }

    @Test
    public void shouldThrowExceptionOnInvalidUrl() {
        assertThrows(
            IllegalArgumentException.class,
            () -> RequestUriBuilder.builder()
                .fromUrl("invalid url")
                .build()
        );
    }

    @Test
    public void shouldBuildOnValidUrl() {
        URI uri = RequestUriBuilder.builder()
            .fromUrl("https://www.facebook.com")
            .build();
        assertEquals("https://www.facebook.com", uri.toString());
    }

    @Test
    public void shouldReplacePathVariable() {
        URI uri = RequestUriBuilder.builder()
            .fromUrl("https://www.facebook.com/{variable}")
            .pathVariable("variable", "value")
            .build();
        assertEquals("https://www.facebook.com/value", uri.toString());
    }

    @Test
    public void shouldThrowExceptionWhenPathVariableNotFound() {
        assertThrows(
            IllegalArgumentException.class,
            () -> RequestUriBuilder.builder()
                .fromUrl("https://www.facebook.com/{variable}")
                .pathVariable("notFound", "value")
                .build()
        );
    }

    @Test
    public void shouldAddQueryParam() {
        URI uri = RequestUriBuilder.builder()
            .fromUrl("https://www.facebook.com")
            .queryParam("variable", "value")
            .build();
        assertEquals("https://www.facebook.com?variable=value", uri.toString());
    }

    @Test
    public void shouldReplaceWrongCharacters() {
        URI uri = RequestUriBuilder.builder()
            .fromUrl("https://www.facebook.com")
            .queryParam("\"", "value")
            .build();
        assertEquals("https://www.facebook.com?%22=value", uri.toString());
    }

    @Test
    public void fullBuilderTest() {
        URI uri = RequestUriBuilder.builder()
            .fromUrl("https://www.facebook.com/{variable}")
            .pathVariable("variable", "value")
            .queryParam("param", "value2")
            .build();
        assertEquals("https://www.facebook.com/value?param=value2", uri.toString());
    }
}
