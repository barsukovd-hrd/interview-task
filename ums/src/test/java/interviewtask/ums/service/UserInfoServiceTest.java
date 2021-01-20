package interviewtask.ums.service;

import static org.mockito.Mockito.when;

import interviewtask.ums.client.External3rdPartyUserServiceClient;
import interviewtask.umsapi.dto.UmsUserInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class UserInfoServiceTest {

  @MockBean
  private External3rdPartyUserServiceClient client;

  @Autowired
  private UserInfoService userInfoService;

  @Test
  void getUserInfoById() {
    // given
    String id = "1";

    UmsUserInfoDto expected = UmsUserInfoDto.builder()
        .rate(1)
        .build();

    // when
    when(client.getExternalUserInfoById(id))
        .thenReturn(Mono.just(expected));

    Mono<UmsUserInfoDto> actual = userInfoService.getUserInfoById(id);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }
}
