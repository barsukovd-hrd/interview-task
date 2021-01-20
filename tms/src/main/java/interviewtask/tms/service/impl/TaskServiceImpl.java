package interviewtask.tms.service.impl;

import interviewtask.tms.domain.Status;
import interviewtask.tms.domain.Task;
import interviewtask.tms.dto.FilterDto;
import interviewtask.tms.dto.FilterDto.SortDirection;
import interviewtask.tms.dto.UpdateTaskDto;
import interviewtask.tms.repository.TaskRepository;
import interviewtask.tms.service.TaskService;
import interviewtask.tms.service.UserService;
import interviewtask.umsapi.util.exception.UmsException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final UserService userService;
  private final TaskRepository taskRepository;

  @Override
  public Mono<Task> createTask(Task request) {
    return Mono.just(taskRepository.save(request));
  }

  @Override
  public Flux<Task> getAllTasks(FilterDto request) {
    List<Task> tasks;

    if (request.getDepartmentId() != null) {
      if (request.getSortDirection() != null &&
          request.getSortDirection() == SortDirection.ASC) {
        tasks = taskRepository.findAllByAuthor_Department_IdOrderByCreatedDateAsc(request.getDepartmentId());
      } else {
        tasks = taskRepository.findAllByAuthor_Department_IdOrderByCreatedDateDesc(request.getDepartmentId());
      }
    } else {
      tasks = (List<Task>) taskRepository.findAll();
    }

    return Flux.fromIterable(tasks);
  }

  @Override
  public Mono<Task> getTaskById(Integer id) {
    return Mono.just(taskRepository.findById(id)
        .orElseThrow(() -> new UmsException("Task with ID '" + id + "' not found")));
  }

  @Override
  public Mono<Task> updateTaskById(Integer id, UpdateTaskDto request) {
    return getTaskById(id)
        .map(task -> updateStatus(task, request.getStatus()))
        .flatMap(task -> {
          if (request.getAssigneeId() != null) {
            return userService.getUserById(request.getAssigneeId())
                .map(user -> {
                  task.setAssignee(user);
                  return task;
                });
          } else {
            return Mono.just(task);
          }
        })
        .flatMap(task -> Mono.just(taskRepository.save(task)));
  }

  private Task updateStatus(Task task, Status status) {
    if (status != null) {
      task.setStatus(status);
    }
    return task;
  }
}
