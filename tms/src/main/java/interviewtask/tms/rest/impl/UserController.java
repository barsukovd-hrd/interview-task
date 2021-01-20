package interviewtask.tms.rest.impl;

import interviewtask.tms.dto.CreateUserDto;
import interviewtask.tms.dto.UserDto;
import interviewtask.tms.mapper.UserMapper;
import interviewtask.tms.rest.UserApi;
import interviewtask.tms.service.DepartmentService;
import interviewtask.tms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserMapper userMapper;
  private final UserService userService;
  private final DepartmentService departmentService;

  @Override
  public Mono<UserDto> createUser(CreateUserDto request) {
    return departmentService.getDepartmentById(request.getDepartmentId())
        .doOnNext(ignore -> log.debug("Create user request: {}", request))
        .map(department -> userMapper.mapCreateUserDto(request, department))
        .flatMap(userService::createUser)
        .map(userMapper::mapUser)
        .doOnNext(rsp -> log.debug("Create user response: {}", rsp));
  }

  @Override
  public Mono<UserDto> getUserById(Integer id) {
    return Mono.just(id)
        .doOnNext(req -> log.debug("Getting user by id request: {}", req))
        .flatMap(userService::getUserById)
        .map(userMapper::mapUser)
        .doOnNext(rsp -> log.debug("Getting user by id response: {}", rsp));
  }
}
