package interviewtask.tms.mapper;

import interviewtask.tms.domain.Attachment;
import interviewtask.tms.domain.Task;
import interviewtask.tms.dto.AttachmentDto;
import interviewtask.tms.dto.CreateAttachmentDto;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapper {

  public Attachment mapCreateAttachmentDto(CreateAttachmentDto source, Task task) {
    return Attachment.builder()
        .url(source.getUrl())
        .task(task)
        .build();
  }

  public AttachmentDto mapAttachment(Attachment source) {
    return AttachmentDto.builder()
        .url(source.getUrl())
        .taskId(source.getTask().getId())
        .build();
  }

  public Set<AttachmentDto> mapAttachments(Set<Attachment> source) {
    Set<AttachmentDto> attachments = new HashSet();

    if (source != null) {
      source.forEach(attachment -> attachments.add(mapAttachment(attachment)));
    }

    return attachments;
  }
}
