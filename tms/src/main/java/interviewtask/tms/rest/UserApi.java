package interviewtask.tms.rest;

import interviewtask.tms.dto.CreateUserDto;
import interviewtask.tms.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
public interface UserApi {

  @Operation(
      summary = "Create new user",
      tags = {"User"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(schema = @Schema(implementation = UserDto.class))
      )
  })
  @PostMapping(
      value = "/users",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  Mono<UserDto> createUser(
      @Parameter(description = "Create new user request. Cannot be null or empty", required = true)
      @Valid @RequestBody CreateUserDto request);

  @Operation(
      summary = "Get user by id",
      tags = {"User"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(schema = @Schema(implementation = UserDto.class))
      )
  })
  @GetMapping(
      value = "/users/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  Mono<UserDto> getUserById(@PathVariable Integer id);
}
