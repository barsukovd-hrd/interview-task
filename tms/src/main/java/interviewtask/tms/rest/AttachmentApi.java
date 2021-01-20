package interviewtask.tms.rest;

import interviewtask.tms.dto.AttachmentDto;
import interviewtask.tms.dto.CreateAttachmentDto;
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
public interface AttachmentApi {

  @Operation(
      summary = "Create new attachment",
      tags = {"Attachment"}
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(schema = @Schema(implementation = AttachmentDto.class))
      )
  })
  @PostMapping(
      value = "/attachments",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  Mono<AttachmentDto> createAttachment(
      @Parameter(description = "Create new attachment request. Cannot be null or empty", required = true)
      @Valid @RequestBody CreateAttachmentDto request);
}
