package it.indra.SigecAPI.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.indra.SigecAPI.exception.LockedResourceException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalAccessException.class})
    public ResponseEntity<Object> illegalException(HttpServletRequest request, IllegalAccessException ex) { 
        return new ResponseEntity<>(getBody(HttpStatus.FORBIDDEN, ex, ex.getMessage(), request), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(value = { InvocationTargetException.class })
    public ResponseEntity<Object> invocationException(HttpServletRequest request, InvocationTargetException ex) { 
        return new ResponseEntity<>(getBody(HttpStatus.INTERNAL_SERVER_ERROR, ex, ex.getMessage(), request), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = { LockedResourceException.class })
    public ResponseEntity<Object> lockException(HttpServletRequest request, LockedResourceException ex) { 
        return new ResponseEntity<>(getBody(HttpStatus.CONFLICT, ex, ex.getMessage(), request), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    
    public Map<String, Object> getBody(HttpStatus status, Exception ex, String message, HttpServletRequest request) {
		
		log.error(status.toString(), message, ex.getMessage());
		log.error("url {}", request.getRequestURI());
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", message);
		body.put("error", status.getReasonPhrase());
		body.put("path", request.getRequestURI());
		body.put("exception", ex.toString());

		Throwable cause = ex.getCause();
		if (cause != null) {
			body.put("exceptionCause", ex.getCause().toString());
		}
		return body;
	} 
}