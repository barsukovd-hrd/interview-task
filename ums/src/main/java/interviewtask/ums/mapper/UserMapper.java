package interviewtask.ums.mapper;

import interviewtask.ums.dto.GetUserInfoDto;
import interviewtask.umsapi.dto.UmsUserInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UmsUserInfoDto mapUser(GetUserInfoDto source);
}
