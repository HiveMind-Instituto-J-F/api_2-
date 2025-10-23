package com.api_rest.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManipuladorGlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return buildResponse("Erro de validação: " + errors, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleValidationErrors(IllegalArgumentException ex, WebRequest request) {
        String errors = ex.getLocalizedMessage();

        return buildResponse("Erro de validação: " + errors, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleValidationErrors(NumberFormatException ex, WebRequest request) {
        String errors = ex.getLocalizedMessage();

        return buildResponse("Erro de validação: " + errors, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleValidationErrors(HttpMessageNotReadableException ex, WebRequest request) {
        String errors = ex.getLocalizedMessage();

        return buildResponse("Erro de formato: " + errors, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Object> handleValidationErrors(HttpMediaTypeNotAcceptableException ex, WebRequest request) {
        String errors = ex.getLocalizedMessage();

        return buildResponse("Erro de formato: " + errors, HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String errors = ex.getLocalizedMessage();

        return buildResponse("Erro de dado: " + errors, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralErrors(Exception ex, WebRequest request) {
        String errors = ex.getLocalizedMessage();
        return buildResponse("Erro interno do servidor: " + errors, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleValidationErrors(NullPointerException ex, WebRequest request) {
        String errors = ex.getLocalizedMessage();

        return buildResponse("Erro de formato" + errors, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(ChangeSetPersister.NotFoundException ex, WebRequest request) {
        return buildResponse("Recurso não encontrado: " + ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // Método utilitário para criar o corpo de resposta
    private ResponseEntity<Object> buildResponse(String message, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(body, status);
    }
}
