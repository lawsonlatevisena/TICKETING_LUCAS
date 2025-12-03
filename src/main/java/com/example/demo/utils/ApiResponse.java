package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public ApiResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters
    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    // -----------------------------
    // Fonction statique send avec message et data optionnels
    // -----------------------------
    public static <T> ResponseEntity<ApiResponse<T>> send(
            HttpStatus status, String message, T data) {

        return new ResponseEntity<>(new ApiResponse<>(status, message, data), status);
    }

    // -----------------------------
    // Surcharge 1 : juste type
    // -----------------------------
    public static <T> ResponseEntity<ApiResponse<T>> send(HttpStatus status) {
        return send(status, null, null);
    }

    // -----------------------------
    // Surcharge 2 : type + message
    // -----------------------------
    public static <T> ResponseEntity<ApiResponse<T>> send(HttpStatus status, String message) {
        return send(status, message, null);
    }

    // -----------------------------
    // Surcharge 3 : type + data
    // -----------------------------
    public static <T> ResponseEntity<ApiResponse<T>> send(HttpStatus status, T data) {
        return send(status, null, data);
    }
}
