package com.example.learnspringsecurity.config.exception;

import com.example.learnspringsecurity.domain.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalRestException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<Object>> handleInternalServerError(Exception e, HttpServletRequest request) {
        RestResponse<Object> customErrorResponse = RestResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomResourceNotfoundException.class)
    public ResponseEntity<RestResponse<Object>> handleResourceNotfound(CustomResourceNotfoundException e, HttpServletRequest request) {
        RestResponse<Object> customErrorResponse = RestResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomResourceExistingException.class)
    public ResponseEntity<RestResponse<Object>> handleResourceIsExisting(CustomResourceExistingException e, HttpServletRequest request) {
        RestResponse<Object> customErrorResponse = RestResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(customErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<RestResponse<Object>> handleBadRequest(CustomBadRequestException e, HttpServletRequest request) {
        RestResponse<Object> customErrorResponse = RestResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomUnauthorizedException.class)
    public ResponseEntity<RestResponse<Object>> handleUnauthorized(CustomUnauthorizedException e, HttpServletRequest request) {
        RestResponse<Object> customErrorResponse = RestResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * global forbidden existing
     ***/
    @ExceptionHandler(CustomForbiddenException.class)
    public ResponseEntity<RestResponse<Object>> handleForbidden(CustomForbiddenException e, HttpServletRequest request) {
        RestResponse<Object> customErrorResponse = RestResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(customErrorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CustomFileStorageException.class)
    public ResponseEntity<RestResponse<Object>> handleResourceIsExisting(CustomFileStorageException e, HttpServletRequest request) {
        RestResponse<Object> customErrorResponse = RestResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
