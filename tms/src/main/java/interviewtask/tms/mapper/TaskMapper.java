package interviewtask.tms.mapper;

import interviewtask.tms.domain.Status;
import interviewtask.tms.domain.Task;
import interviewtask.tms.domain.User;
import interviewtask.tms.dto.CreateTaskDto;
import interviewtask.tms.dto.TaskDto;
import interviewtask.tms.dto.TaskInfoDto;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

  private final UserMapper userMapper;
  private final CommentMapper commentMapper;
  private final AttachmentMapper attachmentMapper;

  public Task mapCreateTaskDto(CreateTaskDto source, User user) {
    return Task.builder()
        .theme(source.getTheme())
        .description(source.getDescription())
        .status(Status.TODO)
        .createdDate(LocalDateTime.now())
        .author(user)
        .build();
  }

  public TaskDto mapTask(Task source) {
    return TaskDto.builder()
        .id(source.getId())
        .theme(source.getTheme())
        .description(source.getDescription())
        .status(source.getStatus())
        .createdDate(source.getCreatedDate())
        .author(userMapper.mapUser(source.getAuthor()))
        .assignee(userMapper.mapUser(source.getAssignee()))
        .build();
  }

  public TaskInfoDto mapTaskInfoDto(Task source) {
    return TaskInfoDto.builder()
        .theme(source.getTheme())
        .description(source.getDescription())
        .status(source.getStatus())
        .createdDate(source.getCreatedDate())
        .author(userMapper.mapUser(source.getAuthor()))
        .assignee(userMapper.mapUser(source.getAssignee()))
        .comments(commentMapper.mapComments(source.getComments()))
        .attachments(attachmentMapper.mapAttachments(source.getAttachments()))
        .build();
  }
}
