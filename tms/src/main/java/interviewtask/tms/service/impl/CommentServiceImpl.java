package interviewtask.tms.service.impl;

import interviewtask.tms.domain.Comment;
import interviewtask.tms.repository.CommentRepository;
import interviewtask.tms.service.CommentService;
import interviewtask.umsapi.util.exception.UmsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Override
  public Mono<Comment> createComment(Comment request) {
    return Mono.just(commentRepository.save(request));
  }

  @Override
  public Mono<Comment> getCommentById(Integer id) {
    return Mono.just(commentRepository.findById(id)
        .orElseThrow(() -> new UmsException("Comment with ID '" + id + "' not found")));
  }

  @Override
  public Mono<Void> deleteCommentById(Integer id) {
    return this.getCommentById(id)
        .map(comment -> {
          comment.setDeleted(true);
          return comment;
        })
        .flatMap(comment -> {
          commentRepository.save(comment);
          return Mono.empty();
        });
  }
}
