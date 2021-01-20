package interviewtask.tms.service;

import interviewtask.tms.domain.Attachment;
import reactor.core.publisher.Mono;

public interface AttachmentService {

  Mono<Attachment> createAttachment(Attachment request);
}
