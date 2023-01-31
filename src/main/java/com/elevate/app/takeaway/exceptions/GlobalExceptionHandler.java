package com.elevate.app.takeaway.exceptions;

import com.elevate.app.takeaway.model.ResponseModel;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ResponseModel response = new ResponseModel();
        response.errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        response.message = "Error processing request";
        response.responseCode = HttpStatus.BAD_REQUEST.value();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ResponseModel> handleEntityNotFound(EntityNotFoundException ex){
        ResponseModel error = new ResponseModel();
        error.errors = Collections.singletonList(ex.getMessage());
        error.responseCode = HttpStatus.NOT_FOUND.value();
        error.message = "Requested content not found";
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<ResponseModel> handleCustomException(CustomException ex){
        ResponseModel error = new ResponseModel();
        error.errors = Collections.singletonList(ex.getMessage());
        error.responseCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        error.message = "There was error processing your request";
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
