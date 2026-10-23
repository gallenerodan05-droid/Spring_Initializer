package com.template.Spring_Initializer.Exception;


import com.template.Spring_Initializer.DTO.ApiError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class )
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex ){
//                                                                                       error-> error.getDefaultMessage() this was the original code but the AI recommended lambda
        List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        ApiError body = new ApiError( 400, "Validation Failed", messages);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }




}
