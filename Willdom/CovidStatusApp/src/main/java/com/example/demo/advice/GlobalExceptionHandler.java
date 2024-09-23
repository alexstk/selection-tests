package com.example.demo.advice;

import com.example.demo.exception.CovidServiceException;
import java.util.Map;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CovidServiceException.class)
  public ResponseEntity<Map<String, Object>> handleCovidServiceException(CovidServiceException e) {
    Map<String, Object> error = Map.of("message", e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.valueOf(404));
  }

  @ExceptionHandler(PropertyReferenceException.class)
  public ResponseEntity<Map<String, Object>> handlePropertyReferenceException(PropertyReferenceException e) {
    Map<String, Object> error = Map.of("message", e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.valueOf(400));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleException(Exception e) {
    Map<String, Object> error = Map.of("message", e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

}
