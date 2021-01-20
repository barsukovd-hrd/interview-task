package interviewtask.umsapi.client;

import interviewtask.umsapi.dto.UmsUserInfoDto;
import interviewtask.umsapi.util.exception.UmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class UserInfoClient {

  private final WebClient webClient;

  private static final String USER_INFO_URI = "/user-info";

  public UserInfoClient(@Value("${services.ums.url}") String url) {
    this.webClient = WebClient
        .builder()
        .baseUrl(url)
        .build();
  }

  public Mono<UmsUserInfoDto> getUserInfoById(Integer id) {
    return webClient
        .get()
        .uri(USER_INFO_URI + "/" + id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, this::handleError)
        .onStatus(HttpStatus::is5xxServerError, this::handleError)
        .bodyToMono(UmsUserInfoDto.class);
  }

  private Mono<UmsException> handleError(ClientResponse response) {
    return response
        .bodyToMono(UmsException.class)
        .map(error -> new UmsException(error.getErrorMessage()));
  }
}
