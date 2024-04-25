package cl.corellana.demouser.adapters.web;

import cl.corellana.demouser.adapters.web.dto.ErrorDTO;
import cl.corellana.demouser.adapters.web.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> customHandleException(BusinessException e){
        return ResponseEntity
                .status(e.getCode())
                .body(ErrorDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .codigo(e.getCode())
                        .detail(e.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleException(MethodArgumentNotValidException e){
        return ResponseEntity
                .status(400)
                .body(ErrorDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .codigo(400)
                        .detail(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage())
                        .build());
    }
}
