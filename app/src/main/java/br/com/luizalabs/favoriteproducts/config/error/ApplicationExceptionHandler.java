package br.com.luizalabs.favoriteproducts.config.error;

import br.com.luizalabs.favoriteproducts.config.error.dto.ErrorResponse;
import br.com.luizalabs.favoriteproducts.domain.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { BusinessException.class })
    protected ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request) {
        return this.handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException e, WebRequest request) {
        String message = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "Could not complete the operation";
        return this.handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        String path =  request.getContextPath() + ((ServletWebRequest) request).getRequest().getServletPath();
        return new ResponseEntity<>(new ErrorResponse((String) body, path), headers, status);
    }
}
