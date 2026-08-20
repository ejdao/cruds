package ejdao.backend.users.infrastructure.repositories;

import ejdao.backend.users.application.requests.UserCreateRequest;
import ejdao.backend.users.application.responses.UserResponse;
import ejdao.backend.users.domain.repositories.UserRepository;
import ejdao.backend.users.infrastructure.factories.UserCreateRequestToUserOrm;
import ejdao.backend.users.infrastructure.factories.UserOrmToUserResponse;
import ejdao.backend.users.infrastructure.jpa.UserJpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserSource implements UserRepository {

  private final UserJpaRepository userJpaRepository;
  private final UserCreateRequestToUserOrm userCreateRequestToUserOrm;
  private final UserOrmToUserResponse userOrmToUserResponse;

  public UserSource(
    UserJpaRepository userJpaRepository,
    UserCreateRequestToUserOrm userCreateRequestToUserOrm,
    UserOrmToUserResponse userOrmToUserResponse
  ) {
    this.userJpaRepository = userJpaRepository;
    this.userCreateRequestToUserOrm = userCreateRequestToUserOrm;
    this.userOrmToUserResponse = userOrmToUserResponse;
  }

  @Override
  public List<UserResponse> fetch() {
    return userJpaRepository
      .findAll()
      .stream()
      .map(userOrmToUserResponse::map)
      .toList();
  }

  @Override
  public UserResponse create(UserCreateRequest request) {
    return userOrmToUserResponse.map(
      userJpaRepository.save(userCreateRequestToUserOrm.map(request))
    );
  }

  @Override
  public boolean existsByDocument(String document) {
    return userJpaRepository.existsByDocument(document);
  }
}
