package interviewtask.tms.service;

import static org.mockito.Mockito.when;

import interviewtask.tms.domain.Department;
import interviewtask.tms.repository.DepartmentRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class DepartmentServiceTest {

  @MockBean
  private DepartmentRepository departmentRepository;

  @Autowired
  private DepartmentService departmentService;

  @Test
  void createDepartment() {
    // given
    Department department = Department.builder()
        .name("Department")
        .build();

    Department expected = Department.builder()
        .id(1)
        .name(department.getName())
        .build();

    // when
    when(departmentRepository.save(department))
        .thenReturn(expected);

    Mono<Department> actual = departmentService.createDepartment(department);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }

  @Test
  void getDepartmentById() {
    // given
    Integer id = 1;

    Department expected = Department.builder()
        .id(id)
        .name("Department")
        .build();

    // when
    when(departmentRepository.findById(id))
        .thenReturn(Optional.of(expected));

    Mono<Department> actual = departmentService.getDepartmentById(id);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }
}
