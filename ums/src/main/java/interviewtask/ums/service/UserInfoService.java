package interviewtask.ums.service;

import interviewtask.umsapi.dto.UmsUserInfoDto;
import reactor.core.publisher.Mono;

public interface UserInfoService {

  Mono<UmsUserInfoDto> getUserInfoById(String id);
}
