package interviewtask.tms.service;

import interviewtask.tms.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<User> createUser(User request);

  Mono<User> getUserById(Integer id);
}
