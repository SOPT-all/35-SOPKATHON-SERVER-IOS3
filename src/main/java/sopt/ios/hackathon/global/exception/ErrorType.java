package sopt.ios.hackathon.global.exception;


import org.springframework.http.HttpStatus;

public enum ErrorType {

    INVALID_FIELD_ERROR(HttpStatus.BAD_REQUEST, "40002", "요청 본문의 필드 값이 허용된 형식과 다릅니다."),
    NO_REQUEST_HEADER_ERROR(HttpStatus.BAD_REQUEST, "40004", "요청에 필요한 헤더가 존재하지 않습니다."),
    DATA_INTEGRITY_VIOLATION_ERROR(HttpStatus.BAD_REQUEST, "40007", "데이터 무결성 제약 조건을 위반했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "50001", "예상치 못한 서버 에러가 발생했습니다."),
    NOT_FOUND_MEMBER_ERROR(HttpStatus.NOT_FOUND, "40401", "존재하지 않는 사용자입니다")

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    private ErrorType(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}