package interviewtask.tms.rest.impl;

import interviewtask.tms.dto.AttachmentDto;
import interviewtask.tms.dto.CreateAttachmentDto;
import interviewtask.tms.mapper.AttachmentMapper;
import interviewtask.tms.rest.AttachmentApi;
import interviewtask.tms.service.AttachmentService;
import interviewtask.tms.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AttachmentController implements AttachmentApi {

  private final TaskService taskService;
  private final AttachmentMapper attachmentMapper;
  private final AttachmentService attachmentService;

  @Override
  public Mono<AttachmentDto> createAttachment(CreateAttachmentDto request) {
    log.debug("Create attachment request: {}", request);
    return taskService.getTaskById(request.getTaskId())
        .map(task -> attachmentMapper.mapCreateAttachmentDto(request, task))
        .flatMap(attachmentService::createAttachment)
        .map(attachmentMapper::mapAttachment)
        .doOnNext(rsp -> log.debug("Create attachment response: {}", rsp));
  }
}
