package ejdao.backend.users.infrastructure.repositories;

import ejdao.backend.users.application.responses.UserResponse;
import ejdao.backend.users.domain.repositories.UserRepository;
import ejdao.backend.users.infrastructure.factories.UserOrmToUserResponse;
import ejdao.backend.users.infrastructure.jpa.UserJpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserSource implements UserRepository {

  private final UserJpaRepository userJpaRepository;
  private final UserOrmToUserResponse userOrmToUserResponse;

  public UserSource(
      UserJpaRepository userJpaRepository, UserOrmToUserResponse userOrmToUserResponse) {
    this.userJpaRepository = userJpaRepository;
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
}
