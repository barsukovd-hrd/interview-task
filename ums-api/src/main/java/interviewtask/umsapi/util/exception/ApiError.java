package interviewtask.umsapi.util.exception;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

  private Instant ts;
  private String errorMessage;
}
