package interviewtask.tms.repository;

import interviewtask.tms.domain.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Integer> {

}
