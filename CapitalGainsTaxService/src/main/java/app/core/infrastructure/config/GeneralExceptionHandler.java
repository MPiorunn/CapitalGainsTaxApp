package app.core.infrastructure.config;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity<Object> handleIllegalInputParameters(Exception ex, WebRequest request) {
        var badRequestResponse = new BadRequestResponse(ex.getLocalizedMessage(),
            ((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, badRequestResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
            request);
    }

    @Data
    private static class BadRequestResponse {

        private BadRequestResponse(String message, String url) {
            this.timestamp = LocalDateTime.now();
            this.message = message;
            this.url = url;
            this.status = HttpStatus.BAD_REQUEST.value() + " : " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        }

        private final LocalDateTime timestamp;
        private final String message;
        private final String url;
        private final String status;
    }
}
