package interviewtask.tms.service;

import static org.mockito.Mockito.when;

import interviewtask.tms.domain.Department;
import interviewtask.tms.domain.Status;
import interviewtask.tms.domain.Task;
import interviewtask.tms.domain.User;
import interviewtask.tms.dto.FilterDto;
import interviewtask.tms.dto.FilterDto.SortDirection;
import interviewtask.tms.repository.TaskRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class TaskServiceTest {

  @MockBean
  private TaskRepository taskRepository;

  @MockBean
  private UserService userService;

  @Autowired
  private TaskService taskService;

  @Test
  void createTask() {
    // given
    User user = User.builder()
        .id(1)
        .name("User")
        .build();

    Task task = Task.builder()
        .theme("Task")
        .description("Description")
        .status(Status.TODO)
        .createdDate(LocalDateTime.now())
        .author(user)
        .build();

    Task expected = Task.builder()
        .id(1)
        .theme("Task")
        .description("Description")
        .status(Status.TODO)
        .createdDate(LocalDateTime.now())
        .author(user)
        .build();

    // when
    when(taskRepository.save(task))
        .thenReturn(expected);

    Mono<Task> actual = taskService.createTask(task);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }

  @Test
  void getAllTasksByDepartmentId() {
    // given
    FilterDto request = FilterDto.builder()
        .departmentId(1)
        .sortDirection(SortDirection.ASC)
        .build();

    Department department = Department.builder()
        .id(request.getDepartmentId())
        .name("Department")
        .build();

    User user = User.builder()
        .id(1)
        .name("User")
        .department(department)
        .build();

    Task expected_1 = Task.builder()
        .id(1)
        .theme("Task 1")
        .description("Description")
        .status(Status.TODO)
        .createdDate(LocalDateTime.now())
        .author(user)
        .build();

    Task expected_2 = Task.builder()
        .id(2)
        .theme("Task 2")
        .description("Description")
        .status(Status.TODO)
        .createdDate(LocalDateTime.now())
        .author(user)
        .build();

    // when
    when(taskRepository.findAllByAuthor_Department_IdOrderByCreatedDateAsc(request.getDepartmentId()))
        .thenReturn(List.of(expected_1, expected_2));

    Flux<Task> actual = taskService.getAllTasks(request);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  void getAllTasks() {
    // given
    FilterDto request = FilterDto.builder()
        .departmentId(null)
        .sortDirection(SortDirection.ASC)
        .build();

    Department department = Department.builder()
        .id(1)
        .name("Department")
        .build();

    User user = User.builder()
        .id(1)
        .name("User")
        .department(department)
        .build();

    Task expected_1 = Task.builder()
        .id(1)
        .theme("Task 1")
        .description("Description")
        .status(Status.TODO)
        .createdDate(LocalDateTime.now())
        .author(user)
        .build();

    Task expected_2 = Task.builder()
        .id(2)
        .theme("Task 2")
        .description("Description")
        .status(Status.TODO)
        .createdDate(LocalDateTime.now())
        .author(user)
        .build();

    // when
    when(taskRepository.findAll())
        .thenReturn(List.of(expected_1, expected_2));

    Flux<Task> actual = taskService.getAllTasks(request);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  void getTaskById() {
    // given
    Integer id = 1;

    Department department = Department.builder()
        .id(1)
        .name("Department")
        .build();

    User user = User.builder()
        .id(1)
        .name("User")
        .department(department)
        .build();

    Task expected = Task.builder()
        .id(id)
        .theme("Task 1")
        .description("Description")
        .status(Status.TODO)
        .createdDate(LocalDateTime.now())
        .author(user)
        .build();

    // when
    when(taskRepository.findById(id))
        .thenReturn(Optional.of(expected));

    Mono<Task> actual = taskService.getTaskById(id);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }
}
