package interviewtask.ums.service.impl;

import interviewtask.ums.client.External3rdPartyUserServiceClient;
import interviewtask.ums.service.UserInfoService;
import interviewtask.umsapi.dto.UmsUserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

  private final External3rdPartyUserServiceClient client;

  @Override
  public Mono<UmsUserInfoDto> getUserInfoById(String id) {
    return client.getExternalUserInfoById(id);
  }
}
