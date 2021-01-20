package interviewtask.tms.service;

import static org.mockito.Mockito.when;

import interviewtask.tms.domain.Department;
import interviewtask.tms.domain.User;
import interviewtask.tms.repository.UserRepository;
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
class UserServiceTest {

  @MockBean
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

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
