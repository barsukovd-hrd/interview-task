package interviewtask.ums.util;

import interviewtask.ums.util.exception.UserInfoServiceInteractionException;
import interviewtask.umsapi.dto.UmsUserInfoDto;
import interviewtask.umsapi.util.exception.ApiError;
import interviewtask.umsapi.util.exception.UmsException;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class UmsExceptionHandler {

  private static final Integer DEFAULT_RATE = 0;

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ApiError> handleException(Exception ex) {
    log.error("Handling Exception ", ex);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ApiError(Instant.now(), ex.getMessage()));
  }

  @ExceptionHandler(UmsException.class)
  protected ResponseEntity<ApiError> handleUmsException(UmsException ex) {
    log.error("Handling UmsException ", ex);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ApiError(ex.getTs(), ex.getErrorMessage()));
  }

  @ExceptionHandler(UserInfoServiceInteractionException.class)
  protected ResponseEntity<UmsUserInfoDto> handleUserInfoServiceInteractionException(UserInfoServiceInteractionException ex) {
    log.error("Handling UserInfoServiceInteractionException ", ex);
    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(UmsUserInfoDto.builder()
            .rate(DEFAULT_RATE)
            .build());
  }
}
