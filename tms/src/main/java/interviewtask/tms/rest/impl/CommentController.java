package interviewtask.tms.rest.impl;

import interviewtask.tms.domain.Task;
import interviewtask.tms.domain.User;
import interviewtask.tms.dto.CommentDto;
import interviewtask.tms.dto.CreateCommentDto;
import interviewtask.tms.mapper.CommentMapper;
import interviewtask.tms.rest.CommentApi;
import interviewtask.tms.service.CommentService;
import interviewtask.tms.service.TaskService;
import interviewtask.tms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {

  private final TaskService taskService;
  private final UserService userService;
  private final CommentService commentService;
  private final CommentMapper commentMapper;

  @Override
  public Mono<CommentDto> createComment(CreateCommentDto request) {
    Mono<Task> task = taskService.getTaskById(request.getTaskId());
    Mono<User> author = userService.getUserById(request.getAuthorId());

    return Mono.zip(task, author)
        .doOnNext(user -> log.debug("Create comment request: {}", request))
        .map(tuple2 -> commentMapper.mapCreateCommentDto(request, tuple2.getT1(), tuple2.getT2()))
        .flatMap(commentService::createComment)
        .map(commentMapper::mapComment)
        .doOnNext(rsp -> log.debug("Create comment response: {}", rsp));
  }

  @Override
  public Mono<Void> deleteCommentById(Integer id) {
    return commentService.deleteCommentById(id);
  }
}
