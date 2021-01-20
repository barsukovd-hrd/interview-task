package interviewtask.tms.service.impl;

import interviewtask.tms.domain.Department;
import interviewtask.tms.repository.DepartmentRepository;
import interviewtask.tms.service.DepartmentService;
import interviewtask.umsapi.util.exception.UmsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Override
  public Mono<Department> createDepartment(Department request) {
    return Mono.just(departmentRepository.save(request));
  }

  @Override
  public Mono<Department> getDepartmentById(Integer id) {
    return Mono.just(departmentRepository.findById(id)
        .orElseThrow(() -> new UmsException("Department with ID '" + id + "' not found")));
  }
}
