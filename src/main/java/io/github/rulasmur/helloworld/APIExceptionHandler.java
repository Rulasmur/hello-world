package io.github.rulasmur.helloworld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rulasmur.helloworld.exceptions.APIExistsException;
import io.github.rulasmur.helloworld.exceptions.APIInvalidParameterException;
import io.github.rulasmur.helloworld.exceptions.APINotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(APIExceptionHandler.class);


    @org.springframework.web.bind.annotation.ExceptionHandler(value = {APIExistsException.class, APIInvalidParameterException.class, APINotFoundException.class})
    protected ResponseEntity<Object> handleExists(RuntimeException ex, WebRequest request) {

        String message = "Unknown";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if(ex instanceof APIExistsException)
        {
            message = "Key Exists";

        }else if(ex instanceof APIInvalidParameterException)
        {
            message = "Parameter cannot be null";
        }else if(ex instanceof APINotFoundException)
        {
            message = "Key does not exists";
            status = HttpStatus.NOT_FOUND;
        }

        return getGenericResponse(message, status);
    }

    private ResponseEntity<Object> getGenericResponse(String message, HttpStatus status) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = "";
        try {
            jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error("Failed to create json", e);
        }
        return new ResponseEntity<>(jsonResult, status);
    }


}
