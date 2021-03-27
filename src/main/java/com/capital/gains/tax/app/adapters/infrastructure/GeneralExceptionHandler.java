package com.capital.gains.tax.app.adapters.infrastructure;

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
        BadRequestResponse badRequestResponse = new BadRequestResponse(ex.getLocalizedMessage(),
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
    /*

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        return handleIllegalInputParameters(ex, request);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleIllegalInputParameters(ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleIllegalInputParameters(ex, request);
    }

    @ExceptionHandler({IllegalArgumentException.class, ConversionFailedException.class, DebitorNotFoundException.class,
        MachineNotFoundException.class})
    @ResponseBody
    public ResponseEntity<Object> handleIllegalInputParameters(Exception ex, WebRequest request) {
        BadRequestResponse badRequestResponse = new BadRequestResponse(ex.getLocalizedMessage(),
            ((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, badRequestResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
            request);
    }


    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseBody
    public ResponseEntity<Object> handlePropertyReferenceException(Exception ex, WebRequest request) {
        String sortValue = request.getParameter("sort");
        BadRequestResponse badRequestResponse = new BadRequestResponse(
            String.format("Cannot use value '%s' to sort!", sortValue),
            ((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, badRequestResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
            request);
    }


     */


}
