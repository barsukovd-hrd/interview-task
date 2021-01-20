package interviewtask.tms.service;

import static org.mockito.Mockito.when;

import interviewtask.tms.domain.Department;
import interviewtask.tms.domain.User;
import interviewtask.tms.repository.UserRepository;
import interviewtask.tms.service.impl.UserServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  private UserService userService;

  @BeforeEach
  void setUp() {
    userService = new UserServiceImpl(userRepository);
  }

  @Test
  void createUser() {
    // given
    Department department = Department.builder()
        .id(1)
        .name("Department")
        .build();

    User user = User.builder()
        .name("User")
        .department(department)
        .build();

    User expected = User.builder()
        .id(1)
        .name(user.getName())
        .department(user.getDepartment())
        .build();

    // when
    when(userRepository.save(user))
        .thenReturn(expected);

    Mono<User> actual = userService.createUser(user);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }

  @Test
  void getUserById() {
    // given
    Integer id = 1;

    Department department = Department.builder()
        .id(1)
        .name("Department")
        .build();

    User expected = User.builder()
        .id(id)
        .name("User")
        .department(department)
        .build();

    // when
    when(userRepository.findById(id))
        .thenReturn(Optional.of(expected));

    Mono<User> actual = userService.getUserById(id);

    // then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(expected)
        .verifyComplete();
  }
}
