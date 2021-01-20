package interviewtask.tms.service;

import static org.mockito.Mockito.when;

import interviewtask.tms.domain.Attachment;
import interviewtask.tms.domain.Status;
import interviewtask.tms.domain.Task;
import interviewtask.tms.repository.AttachmentRepository;
import interviewtask.tms.service.impl.AttachmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class AttachmentServiceTest {

  @Mock
  private AttachmentRepository attachmentRepository;
  private AttachmentService attachmentService;

  @BeforeEach
  void setUp() {
    attachmentService = new AttachmentServiceImpl(attachmentRepository);
  }

  @Test
  void createAttachment() {
    // given
    Task task = Task.builder()
        .theme("Task")
        .status(Status.TODO)
        .build();

    Attachment attachment = Attachment.builder()
        .url("Attachment")
        .task(task)
        .build();

    Attachment expected = Attachment.builder()
        .id(1)
        .url("Attachment")
        .task(task)
        .build();

    // when
    when(attachmentRepository.save(attachment))
        .thenReturn(expected);

    Mono<Attachment> actual = attachmentService.createAttachment(attachment);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }
}
