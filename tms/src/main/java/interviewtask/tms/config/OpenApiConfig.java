package interviewtask.tms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .addServersItem(new Server().url("http://localhost:8080"))
        .info(
            new Info()
                .title("TSM API")
                .description("Task Management System API")
                .contact(new Contact().email("email@email.com"))
                .license(new License().name("OUR LICENCE")));
  }
}
