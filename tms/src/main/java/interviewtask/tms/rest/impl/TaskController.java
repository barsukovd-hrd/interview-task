package interviewtask.tms.rest.impl;

import interviewtask.tms.dto.CreateTaskDto;
import interviewtask.tms.dto.FilterDto;
import interviewtask.tms.dto.TaskDto;
import interviewtask.tms.dto.TaskInfoDto;
import interviewtask.tms.dto.UpdateTaskDto;
import interviewtask.tms.dto.UserRateDto;
import interviewtask.tms.mapper.TaskMapper;
import interviewtask.tms.rest.TaskApi;
import interviewtask.tms.service.TaskService;
import interviewtask.tms.service.UserService;
import interviewtask.umsapi.client.UserInfoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {

  private final TaskMapper taskMapper;
  private final TaskService taskService;
  private final UserService userService;
  private final UserInfoClient userInfoClient;

  @Override
  public Mono<TaskDto> createTask(CreateTaskDto request) {
    return userService.getUserById(request.getAuthorId())
        .doOnNext(user -> log.debug("Create task request: {}", request))
        .map(user -> taskMapper.mapCreateTaskDto(request, user))
        .flatMap(taskService::createTask)
        .map(taskMapper::mapTask)
        .doOnNext(rsp -> log.debug("Create task response: {}", rsp));
  }

  @Override
  public Flux<TaskDto> getAllTasks(FilterDto request) {
    return Flux.just(request)
        .doOnNext(req -> log.debug("Get all task request: {}", req))
        .flatMap(taskService::getAllTasks)
        .map(taskMapper::mapTask)
        .flatMap(taskDto -> userInfoClient.getUserInfoById(taskDto.getId()) // TODO Need to refactor, move to service and add cashing
            .map(umsUserInfoDto -> {
              UserRateDto rate = UserRateDto.builder()
                  .rate(umsUserInfoDto.getRate())
                  .build();
              log.info("UserRateDto: {}", rate);
              taskDto.getAuthor().setUserRate(rate);
              if (taskDto.getAssignee() != null) {
                taskDto.getAssignee().setUserRate(rate);
              }
              return taskDto;
            }))
        .doOnNext(rsp -> log.debug("Get all task response: {}", rsp));
  }

  @Override
  public Mono<TaskInfoDto> getTaskById(Integer id) {
    return Mono.just(id)
        .doOnNext(req -> log.debug("Getting task by id request: {}", req))
        .flatMap(taskService::getTaskById)
        .map(taskMapper::mapTaskInfoDto)
        .doOnNext(rsp -> log.debug("Getting task by id response: {}", rsp));
  }

  @Override
  public Mono<TaskDto> updateTaskById(Integer id, UpdateTaskDto request) {
    return Mono.just(request)
        .doOnNext(req -> log.debug("Update task by id request: {}", req))
        .flatMap(req -> taskService.updateTaskById(id, req))
        .map(taskMapper::mapTask)
        .doOnNext(rsp -> log.debug("Update task by id response: {}", rsp));
  }
}
