package interviewtask.umsapi.api;

import interviewtask.umsapi.dto.UmsUserInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Validated
@RequestMapping("/user-info")
public interface UserInfoApi {

  @Operation(
      summary = "Get user info by id",
      tags = {"User info"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(schema = @Schema(implementation = UmsUserInfoDto.class))
      )
  })
  @GetMapping(
      value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  Mono<UmsUserInfoDto> getUserInfoById(@PathVariable String id);
}
