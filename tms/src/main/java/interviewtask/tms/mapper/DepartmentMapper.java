package interviewtask.tms.mapper;

import interviewtask.tms.domain.Department;
import interviewtask.tms.dto.CreateDepartmentDto;
import interviewtask.tms.dto.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

  Department createDepartmentDto(CreateDepartmentDto source);

  DepartmentDto mapDepartment(Department source);
}
