package interviewtask.umsapi.config;

import interviewtask.umsapi.client.UserInfoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
public class UserInfoClientConfig {

  @Bean
  public UserInfoClient createUserInfoClient(@Value("${services.ums.url}") String url) {
    return new UserInfoClient(url);
  }
}
