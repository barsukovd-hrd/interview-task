package interviewtask.tms.dto;

import interviewtask.tms.domain.Status;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

  private Integer id;
  private String theme;
  private String description;
  private Status status;
  private LocalDateTime createdDate;
  private UserDto author;
  private UserDto assignee;
}
