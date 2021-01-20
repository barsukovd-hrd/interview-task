package interviewtask.tms.service;

import interviewtask.tms.domain.Comment;
import reactor.core.publisher.Mono;

public interface CommentService {

  Mono<Comment> createComment(Comment request);

  Mono<Comment> getCommentById(Integer id);

  Mono<Void> deleteCommentById(Integer id);
}
