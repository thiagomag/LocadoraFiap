package br.com.postechfiap.locadoraveiculosfiap.application.handler;

import br.com.postechfiap.locadoraveiculosfiap.application.handler.exceptions.ClienteNotFoundException;
import br.com.postechfiap.locadoraveiculosfiap.application.handler.exceptions.VeiculoNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Optional;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleException(Exception exception, HttpServletRequest request) {
        final var message = Optional.ofNullable(exception.getMessage()).orElse("Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", Map.of(
                        "code", "500",
                        "message", message,
                        "origin", request.getRequestURI()
                )));
    }

    @ExceptionHandler({ClienteNotFoundException.class, VeiculoNotFoundException.class})
    public ResponseEntity<Map<String, Map<String, Object>>> handleNotFoundException(RuntimeException exception, HttpServletRequest request) {
        final var message = Optional.ofNullable(exception.getMessage()).orElse("Entity Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", Map.of(
                        "code", HttpStatus.NOT_FOUND.value(),
                        "message", message,
                        "origin",  request.getRequestURI()
                )));
    }
}
