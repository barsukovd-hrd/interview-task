package interviewtask.ums.rest;

import interviewtask.ums.service.UserInfoService;
import interviewtask.umsapi.api.UserInfoApi;
import interviewtask.umsapi.dto.UmsUserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserInfoController implements UserInfoApi {

  private final UserInfoService userInfoService;

  @Override
  public Mono<UmsUserInfoDto> getUserInfoById(String id) {
    return userInfoService.getUserInfoById(id);
  }
}
