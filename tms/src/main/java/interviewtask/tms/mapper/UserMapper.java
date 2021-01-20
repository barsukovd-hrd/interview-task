package interviewtask.tms.mapper;

import interviewtask.tms.domain.Department;
import interviewtask.tms.domain.User;
import interviewtask.tms.dto.CreateUserDto;
import interviewtask.tms.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final DepartmentMapper departmentMapper;

  public User mapCreateUserDto(CreateUserDto source, Department department) {
    return User.builder()
        .name(source.getName())
        .department(department)
        .build();
  }

  public UserDto mapUser(User source) {
    if (source == null) {
      return null;
    }

    return UserDto.builder()
        .id(source.getId())
        .name(source.getName())
        .department(departmentMapper.mapDepartment(source.getDepartment()))
        .userRate(null) // TODO To be refactored
        .build();
  }
}
