package interviewtask.tms.service;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import interviewtask.tms.domain.Comment;
import interviewtask.tms.domain.Status;
import interviewtask.tms.domain.Task;
import interviewtask.tms.domain.User;
import interviewtask.tms.repository.CommentRepository;
import interviewtask.tms.service.impl.CommentServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

  @Mock
  private CommentRepository commentRepository;

  private CommentService commentService;

  @BeforeEach
  void setUp() {
    commentService = new CommentServiceImpl(commentRepository);
  }

  @Test
  void createComment() {
    // given
    Task task = Task.builder()
        .id(1)
        .status(Status.TODO)
        .theme("Task")
        .build();

    User user = User.builder()
        .id(1)
        .name("User")
        .build();

    Comment comment = Comment.builder()
        .text("Comment")
        .author(user)
        .task(task)
        .build();

    Comment expected = Comment.builder()
        .id(1)
        .text(comment.getText())
        .author(comment.getAuthor())
        .task(comment.getTask())
        .build();

    // when
    when(commentRepository.save(comment))
        .thenReturn(expected);

    Mono<Comment> actual = commentService.createComment(comment);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }

  @Test
  void getCommentById() {
    // given
    Integer id = 1;

    Task task = Task.builder()
        .id(1)
        .status(Status.TODO)
        .theme("Task")
        .build();

    User user = User.builder()
        .id(1)
        .name("User")
        .build();

    Comment expected = Comment.builder()
        .id(id)
        .text("Comment")
        .author(user)
        .task(task)
        .build();

    // when
    when(commentRepository.findById(id))
        .thenReturn(Optional.of(expected));

    Mono<Comment> actual = commentService.getCommentById(id);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }

  @Test
  void deleteCommentById() {
    // given
    Integer id = 1;

    Task task = Task.builder()
        .id(1)
        .status(Status.TODO)
        .theme("Task")
        .build();

    User user = User.builder()
        .id(1)
        .name("User")
        .build();

    Comment comment = spy(Comment.builder()
        .id(id)
        .text("Comment")
        .author(user)
        .task(task)
        .build());

    Comment expected = Comment.builder()
        .id(comment.getId())
        .text(comment.getText())
        .author(comment.getAuthor())
        .task(comment.getTask())
        .deleted(true)
        .build();

    // when
    when(commentRepository.findById(id))
        .thenReturn(Optional.of(comment));

    commentService.deleteCommentById(id);

    // then
//    verify(user, times(1)).setDeleted(true);
  }
}
