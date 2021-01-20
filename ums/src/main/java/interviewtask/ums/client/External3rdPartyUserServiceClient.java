package interviewtask.ums.client;

import interviewtask.ums.util.exception.UserInfoServiceInteractionException;
import interviewtask.umsapi.dto.UmsUserInfoDto;
import interviewtask.umsapi.util.exception.UmsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class External3rdPartyUserServiceClient {

  private final WebClient webClient;

  public External3rdPartyUserServiceClient(@Value("${services.user-info.url}") String url) {
    this.webClient = WebClient
        .builder()
        .baseUrl(url)
        .build();
  }

  public Mono<UmsUserInfoDto> getExternalUserInfoById(String id) {
    return webClient
        .get()
        .uri("/" + id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(UmsUserInfoDto.class)
        .onErrorMap(UserInfoServiceInteractionException::new);
  }
}
