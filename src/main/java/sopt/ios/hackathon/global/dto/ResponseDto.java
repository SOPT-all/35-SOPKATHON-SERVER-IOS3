package sopt.ios.hackathon.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import org.springframework.validation.BindingResult;
import sopt.ios.hackathon.global.exception.ErrorType;

@JsonInclude(Include.NON_NULL)
public record ResponseDto<T>(
        String status,
        T data,
        String message,
        Object errors
) {

    public static <T> ResponseDto<T> fail(final ErrorType errorType) {
        return new ResponseDto<>(errorType.getCode(), null, errorType.getMessage(), null);
    }

    public static <T> ResponseDto<T> fail(final ErrorType errorType, final String errorDetail) {
        return new ResponseDto<>(errorType.getCode(), null, errorType.getMessage() + "(" + errorDetail + ")", null);
    }

    public static <T> ResponseDto<T> fail(final ErrorType errorType, final BindingResult bindingResult) {
        return new ResponseDto<>(errorType.getCode(), null, errorType.getMessage(), ValidationError.of(bindingResult));
    }

    @JsonInclude(Include.NON_NULL)
    private record ValidationError(
            String path,
            String field,
            String message
    ) {
        private static List<ValidationError> of(final BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new ValidationError(null, error.getField(), error.getDefaultMessage()))
                    .toList();
        }
    }
}