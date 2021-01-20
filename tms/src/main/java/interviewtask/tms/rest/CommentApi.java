package interviewtask.tms.rest;

import interviewtask.tms.dto.CommentDto;
import interviewtask.tms.dto.CreateCommentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Validated
public interface CommentApi {

  @Operation(
      summary = "Create new comment",
      tags = {"Comment"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(schema = @Schema(implementation = CommentDto.class))
      )
  })
  @PostMapping(value = "/comments",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  Mono<CommentDto> createComment(
      @Parameter(description = "Create new comment request. Cannot be null or empty", required = true)
      @Valid @RequestBody CreateCommentDto request);

  @Operation(
      summary = "Delete comment by id",
      tags = {"Comment"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation"
      )
  })
  @DeleteMapping("/comments/{id}")
  Mono<Void> deleteCommentById(@PathVariable Integer id);
}
