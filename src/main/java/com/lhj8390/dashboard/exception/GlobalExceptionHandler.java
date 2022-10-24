package com.lhj8390.dashboard.exception;

import com.lhj8390.dashboard.model.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        log.error("throw Exception : {}", exception.getMessage());
        return ApiResponse.toResponse
                (HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류로 인해 실패하였습니다.", exception.getMessage());
    }

    @ExceptionHandler(value = NoItemException.class)
    public ResponseEntity<Object> noItemExceptionHandler(NoItemException exception) {
        log.error("throw NoItemException : {}", exception.getMessage());
        return ApiResponse.toResponse
                (HttpStatus.BAD_REQUEST, "데이터를 확인해주세요.", exception.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ApiResponse.toResponse(status, "데이터를 다시 확인해주세요! ", errors);
    }
}
