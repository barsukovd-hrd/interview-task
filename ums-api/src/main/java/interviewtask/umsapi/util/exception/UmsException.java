package interviewtask.umsapi.util.exception;

import java.time.Instant;
import lombok.Getter;

@Getter
public class UmsException extends RuntimeException {

  private Instant ts;
  private String errorMessage;

  public UmsException(String errorMessage) {
    this.ts = Instant.now();
    this.errorMessage = errorMessage;
  }
}
