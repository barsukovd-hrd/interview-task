package interviewtask.tms.rest;

import interviewtask.tms.dto.CreateDepartmentDto;
import interviewtask.tms.dto.DepartmentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Validated
public interface DepartmentApi {

  @Operation(
      summary = "Create new department",
      tags = {"Department"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(schema = @Schema(implementation = DepartmentDto.class))
      )
  })
  @PostMapping(
      value = "/departments",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  Mono<DepartmentDto> createDepartment(
      @Parameter(description = "Create new department. Cannot be null or empty", required = true)
      @Valid @RequestBody CreateDepartmentDto request);
}
