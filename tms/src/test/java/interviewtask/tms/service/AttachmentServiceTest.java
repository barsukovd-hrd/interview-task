package interviewtask.tms.service;

import static org.mockito.Mockito.when;

import interviewtask.tms.domain.Attachment;
import interviewtask.tms.domain.Status;
import interviewtask.tms.domain.Task;
import interviewtask.tms.repository.AttachmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class AttachmentServiceTest {

  @MockBean
  private AttachmentRepository attachmentRepository;

  @Autowired
  private AttachmentService attachmentService;

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
