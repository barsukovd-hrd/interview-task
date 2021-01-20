package interviewtask.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {

  private String theme;
  private String description;
  private Integer authorId; // TODO Temporary solution, should be obtained by token
}
