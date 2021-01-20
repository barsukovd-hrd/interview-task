package interviewtask.tms.service;

import interviewtask.tms.domain.Department;
import reactor.core.publisher.Mono;

public interface DepartmentService {

  Mono<Department> createDepartment(Department request);

  Mono<Department> getDepartmentById(Integer id);
}
