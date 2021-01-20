package interviewtask.tms.service.impl;

import interviewtask.tms.domain.Attachment;
import interviewtask.tms.repository.AttachmentRepository;
import interviewtask.tms.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

  private final AttachmentRepository attachmentRepository;

  @Override
  public Mono<Attachment> createAttachment(Attachment request) {
    return Mono.just(attachmentRepository.save(request));
  }
}
