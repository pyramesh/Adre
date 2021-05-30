package com.ramesh.resource.ratemanagementservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Ramesh.Yaleru on 5/28/2021
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, BusinessException.class})
    public ResponseEntity<ExceptionResponse> exception(Exception e) {

        log.error("Exception : ", e);
        ExceptionResponse errorInfo = ExceptionResponse.builder()
                .message(e.getLocalizedMessage())
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorInfo);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ExceptionResponse> resourceNotFoundException(Exception e) {
        log.error("Exception : ", e);
        ExceptionResponse errorInfo = ExceptionResponse.builder()
                .message(e.getLocalizedMessage())
                .statusCode(NOT_FOUND.value())
                .build();
        return ResponseEntity.status(NOT_FOUND).body(errorInfo);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException ex) {

        log.error("AccessDeniedException : ", ex);

        ExceptionResponse error = ExceptionResponse.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ExceptionResponse> nullPointerException(Exception e) {

        log.error("Exception : ", e);
        ExceptionResponse errorInfo = ExceptionResponse.builder()
                .message("something went wrong. Please try again")
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorInfo);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {


        log.info("Validation exception: ", ex);

        List<ErrorMessage> errorMessages = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .errorCode(error.getCode())
                    .messageEn(error.getDefaultMessage())
                    .messageAr(error.getDefaultMessage())
                    .build();
            errorMessages.add(errorMessage);
        });
        ExceptionResponse error = ExceptionResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Validation Errors!")
                .errors(errorMessages)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
