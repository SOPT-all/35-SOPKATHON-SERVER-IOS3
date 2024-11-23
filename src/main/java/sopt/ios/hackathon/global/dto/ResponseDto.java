package sopt.ios.hackathon.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import org.springframework.validation.BindingResult;
import sopt.ios.hackathon.global.exception.ErrorType;

@JsonInclude(Include.NON_NULL)
public record ResponseDto<T>(
        String status,
        T data,
        String message,
        Object errors
) {

    public static <T> ResponseDto<T> success(final T data) {
        return new ResponseDto<>(null, data, null, null);
    }

    public static <T> ResponseDto<T> fail(final ErrorType errorType) {
        return new ResponseDto<>(errorType.getCode(), null, errorType.getMessage(), null);
    }

    public static <T> ResponseDto<T> fail(final ErrorType errorType, final String errorDetail) {
        return new ResponseDto<>(errorType.getCode(), null, errorType.getMessage() + "(" + errorDetail + ")", null);
    }

    public static <T> ResponseDto<T> fail(final ErrorType errorType, final BindingResult bindingResult) {
        return new ResponseDto<>(errorType.getCode(), null, errorType.getMessage(), ValidationError.of(bindingResult));
    }

    public static <T> ResponseDto<T> fail(final ErrorType errorType, final Set<ConstraintViolation<?>> violations) {
        return new ResponseDto<>(errorType.getCode(), null, errorType.getMessage(), ValidationError.of(violations));
    }

    @JsonInclude(Include.NON_NULL)
    private record ValidationError(
            String path,
            String field,
            String message
    ) {

        private static List<ValidationError> of(final Set<ConstraintViolation<?>> violations) {
            return violations.stream()
                    .map(violation -> new ValidationError(
                            violation.getPropertyPath().toString(),
                            null,
                            violation.getMessage()
                    ))
                    .toList();
        }

        private static List<ValidationError> of(final BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new ValidationError(null, error.getField(), error.getDefaultMessage()))
                    .toList();
        }
    }
}