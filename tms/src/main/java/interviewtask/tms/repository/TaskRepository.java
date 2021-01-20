package interviewtask.tms.repository;

import interviewtask.tms.domain.Task;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

  List<Task> findAllByAuthor_Department_IdOrderByCreatedDateAsc(Integer id);

  List<Task> findAllByAuthor_Department_IdOrderByCreatedDateDesc(Integer id);
}
