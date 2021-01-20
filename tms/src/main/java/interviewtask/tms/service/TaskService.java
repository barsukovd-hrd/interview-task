package interviewtask.tms.service;

import interviewtask.tms.domain.Task;
import interviewtask.tms.dto.FilterDto;
import interviewtask.tms.dto.UpdateTaskDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {

  Mono<Task> createTask(Task request);

  Flux<Task> getAllTasks(FilterDto request);

  Mono<Task> getTaskById(Integer id);

  Mono<Task> updateTaskById(Integer id, UpdateTaskDto request);
}
