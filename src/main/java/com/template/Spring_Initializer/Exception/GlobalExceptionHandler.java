package com.template.Spring_Initializer.Exception;


import java.util.List;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.template.Spring_Initializer.DTO.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class )
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex ){
//                                                                                       error-> error.getDefaultMessage() this was the original code but the AI recommended lambda
        List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        ApiError body = new ApiError( 400, "Validation Failed", messages);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {
    ApiError body = new ApiError(404, "Not Found", List.of(ex.getMessage()));
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

   @ExceptionHandler(RuntimeException.class)
public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex) {
    ApiError body = new ApiError(500, "Internal Server Error", List.of(ex.getMessage()));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    
}


}
