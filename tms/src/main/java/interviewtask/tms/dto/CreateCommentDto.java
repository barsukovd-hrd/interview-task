package interviewtask.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentDto {

  private String text;
  private Integer taskId;
  private Integer authorId; // TODO Temporary solution, should be obtained by token
}
