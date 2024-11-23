package sopt.ios.hackathon.global.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sopt.ios.hackathon.global.dto.ResponseDto;
import sopt.ios.hackathon.global.exception.BusinessException;
import sopt.ios.hackathon.global.exception.ErrorType;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ResponseDto<?>> handleConstraintViolationException(ConstraintViolationException e) {
//        return ResponseEntity
//                .status(ErrorType.INVALID_PATH_ERROR.getHttpStatus())
//                .body(ResponseDto.fail(ErrorType.INVALID_PATH_ERROR, e.getConstraintViolations()));
//    }

    // @Valid 유효성 검사 시 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(ErrorType.INVALID_FIELD_ERROR.getHttpStatus())
                .body(ResponseDto.fail(ErrorType.INVALID_FIELD_ERROR, e.getBindingResult()));
    }

    // 필수 요청 헤더(@RequestHeader)가 요청에서 누락됐을 시 예외 처리
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResponseDto<?>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        return ResponseEntity
                .status(ErrorType.NO_REQUEST_HEADER_ERROR.getHttpStatus())
                .body(ResponseDto.fail(ErrorType.NO_REQUEST_HEADER_ERROR, e.getHeaderName()));
    }

    // 데이터 무결성 위반 시 예외 처리
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto<?>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity
                .status(ErrorType.DATA_INTEGRITY_VIOLATION_ERROR.getHttpStatus())
                .body(ResponseDto.fail(ErrorType.DATA_INTEGRITY_VIOLATION_ERROR));
    }

    // 비즈니스 로직에서 발생하는 예외 처리
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDto<?>> handleCustomException(BusinessException e) {
        return ResponseEntity
                .status(e.getErrorType().getHttpStatus())
                .body(ResponseDto.fail(e.getErrorType()));
    }

    // 기타 에러 발생 시 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleGeneralException(Exception e) {
        logger.error("알 수 없는 예외 발생: {}", e.getMessage());

        return ResponseEntity
                .status(ErrorType.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ResponseDto.fail(ErrorType.INTERNAL_SERVER_ERROR, e.getMessage()));
    }
}