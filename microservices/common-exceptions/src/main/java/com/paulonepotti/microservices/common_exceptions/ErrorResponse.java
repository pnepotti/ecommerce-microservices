package com.paulonepotti.microservices.common_exceptions;

import java.util.Map;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public record ErrorResponse(String code, String message, Map<String, String> errors, String timestamp, String path) {

    private static String now() {
        return java.time.Instant.now().toString();
    }

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message, null, now(), null);
    }
    
    public static ErrorResponse ofValidation(Map<String, String> errors, String path) {
        return new ErrorResponse("VALIDATION_ERROR", "Error de validación", errors, now(), path);
    }

    public static ErrorResponse ofTypeMismatch(MethodArgumentTypeMismatchException ex, String path) {
        String message = String.format("El parámetro '%s' debe ser del tipo '%s'", ex.getName(), ex.getRequiredType().getSimpleName());
        return new ErrorResponse("TYPE_MISMATCH", message, null, now(), path);
    }

    public static ErrorResponse ofMethodNotSupported(HttpRequestMethodNotSupportedException ex, String path) {
        String message = String.format("El método HTTP '%s' no está soportado para este endpoint.", ex.getMethod());
        return new ErrorResponse("METHOD_NOT_SUPPORTED", message, null, now(), path);
    }

    public static ErrorResponse ofUnreadableMessage(String path) {
        String message = "El cuerpo de la petición tiene un formato JSON inválido.";
        return new ErrorResponse("INVALID_REQUEST_BODY", message, null, now(), path);
    }

    public static ErrorResponse ofMissingParameter(MissingServletRequestParameterException ex, String path) {
        String message = String.format("Falta el parámetro requerido: %s", ex.getParameterName());
        return new ErrorResponse("MISSING_PARAMETER", message, null, now(), path);
    }

    public static ErrorResponse ofNoResourceFound() {
        return new ErrorResponse("NO_RESOURCE_FOUND", "El recurso solicitado no existe.", null, now(), null);
    }

    public static ErrorResponse ofNoConnectException() {
        return new ErrorResponse("CONNECTION_ERROR", "Error de conexión", null, now(), null);
    }

    public static ErrorResponse ofServiceUnavailable() {
        return new ErrorResponse(
            "SERVICE_UNAVAILABLE", 
            "Servicio temporalmente no disponible. Intente nuevamente más tarde.", 
            null, 
            now(), 
            null
        );
    }

    public static ErrorResponse ofAccessDenied() {
        return new ErrorResponse("ACCESS_DENIED", "Acceso denegado", null, now(), null);
    }

    public static ErrorResponse ofGeneric() {
        return new ErrorResponse(
            "INTERNAL_ERROR",
            "Ha ocurrido un error inesperado. Por favor, inténtalo de nuevo más tarde.",
            null,
            now(),
            null
        );
    }
}
