package interviewtask.tms.rest;

import interviewtask.tms.dto.CreateTaskDto;
import interviewtask.tms.dto.FilterDto;
import interviewtask.tms.dto.TaskDto;
import interviewtask.tms.dto.TaskInfoDto;
import interviewtask.tms.dto.UpdateTaskDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
public interface TaskApi {

  @Operation(
      summary = "Create new task",
      tags = {"Task"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(schema = @Schema(implementation = TaskDto.class))
      )
  })
  @PostMapping(
      value = "/tasks",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  Mono<TaskDto> createTask(
      @Parameter(description = "Create new task request. Cannot be null or empty", required = true)
      @Valid @RequestBody CreateTaskDto request);

  @Operation(
      summary = "Get all tasks",
      tags = {"Task"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(array = @ArraySchema(schema =  @Schema(implementation = TaskDto.class)))
      )
  })
  @GetMapping(
      value = "/tasks",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  Flux<TaskDto> getAllTasks(
      @Parameter(description = "Get all tasks request.", required = true)
      @Valid @RequestBody FilterDto request);

  @Operation(
      summary = "Get task by id",
      tags = {"Task"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(schema = @Schema(implementation = TaskDto.class))
      )
  })
  @GetMapping(
      value = "/tasks/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  Mono<TaskInfoDto> getTaskById(@PathVariable Integer id);

  @Operation(
      summary = "Update task by id",
      tags = {"Task"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(schema = @Schema(implementation = TaskDto.class))
      )
  })
  @PatchMapping(
      value = "/tasks/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  Mono<TaskDto> updateTaskById(
      @PathVariable Integer id,
      @Parameter(description = "Update task by id request. Cannot be null or empty", required = true)
      @Valid @RequestBody UpdateTaskDto request);
}
