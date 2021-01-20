package interviewtask.tms.service;

import static org.mockito.Mockito.when;

import interviewtask.tms.domain.Department;
import interviewtask.tms.repository.DepartmentRepository;
import interviewtask.tms.service.impl.DepartmentServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

  @Mock
  private DepartmentRepository departmentRepository;

  private DepartmentService departmentService;

  @BeforeEach
  void setUp() {
    departmentService = new DepartmentServiceImpl(departmentRepository);
  }

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
