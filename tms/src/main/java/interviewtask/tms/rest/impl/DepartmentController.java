package interviewtask.tms.rest.impl;

import interviewtask.tms.dto.CreateDepartmentDto;
import interviewtask.tms.dto.DepartmentDto;
import interviewtask.tms.mapper.DepartmentMapper;
import interviewtask.tms.rest.DepartmentApi;
import interviewtask.tms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentApi {

  private final DepartmentMapper departmentMapper;
  private final DepartmentService departmentService;

  @Override
  public Mono<DepartmentDto> createDepartment(CreateDepartmentDto request) {
    return Mono.just(request)
        .doOnNext(req -> log.debug("Create department request: {}", req))
        .map(departmentMapper::createDepartmentDto)
        .flatMap(departmentService::createDepartment)
        .map(departmentMapper::mapDepartment)
        .doOnNext(rsp -> log.debug("Create department response: {}", rsp));
  }
}
