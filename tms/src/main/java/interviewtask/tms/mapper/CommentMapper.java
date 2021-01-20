package interviewtask.tms.mapper;

import interviewtask.tms.domain.Comment;
import interviewtask.tms.domain.Task;
import interviewtask.tms.domain.User;
import interviewtask.tms.dto.CommentDto;
import interviewtask.tms.dto.CreateCommentDto;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public Comment mapCreateCommentDto(CreateCommentDto source, Task task, User user) {
    return Comment.builder()
        .text(source.getText())
        .task(task)
        .author(user)
        .build();
  }

  public CommentDto mapComment(Comment source) {
    return CommentDto.builder()
        .id(source.getId())
        .text(source.getText())
        .authorName(source.getAuthor().getName())
        .build();
  }

  public Set<CommentDto> mapComments(Set<Comment> source) {
    Set<CommentDto> comments = new HashSet();

    if (source != null) {
      source.stream()
          .filter(comment -> !comment.isDeleted())
          .forEach(comment -> comments.add(mapComment(comment)));
    }

    return comments;
  }
}
