package interviewtask.tms.service.impl;

import interviewtask.tms.domain.User;
import interviewtask.tms.repository.UserRepository;
import interviewtask.tms.service.UserService;
import interviewtask.umsapi.util.exception.UmsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Mono<User> createUser(User request) {
    return Mono.just(userRepository.save(request));
  }

  @Override
  public Mono<User> getUserById(Integer id) {
    return Mono.just(userRepository.findById(id)
        .orElseThrow(() -> new UmsException("User with ID '" + id + "' not found")));
  }
}
