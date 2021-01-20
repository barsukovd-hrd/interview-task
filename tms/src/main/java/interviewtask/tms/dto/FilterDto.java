package interviewtask.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {

  private Integer departmentId;
  private SortDirection sortDirection;

  public enum SortDirection {
    ASC,
    DESC
  }
}
