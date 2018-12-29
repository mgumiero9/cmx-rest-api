package application.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandlers extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> errorJson = new HashMap<>();

        errorJson.put("Error: ", e.getLocalizedMessage());
        JsonNode jsonNode = objectMapper.valueToTree(errorJson);

        return new ResponseEntity<>(jsonNode, HttpStatus.BAD_REQUEST);
    }
}
