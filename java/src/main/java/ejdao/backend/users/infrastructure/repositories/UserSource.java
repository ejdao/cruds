package ejdao.backend.users.infrastructure.repositories;

import ejdao.backend.users.application.requests.UserCreateRequest;
import ejdao.backend.users.application.requests.UserUpdateRequest;
import ejdao.backend.users.application.responses.UserResponse;
import ejdao.backend.users.domain.repositories.UserRepository;
import ejdao.backend.users.infrastructure.factories.UserCreateRequestToUserOrm;
import ejdao.backend.users.infrastructure.factories.UserOrmToUserResponse;
import ejdao.backend.users.infrastructure.factories.UserUpdateRequestToUserOrm;
import ejdao.backend.users.infrastructure.jpa.UserJpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserSource implements UserRepository {

  private final UserJpaRepository userJpaRepository;
  private final UserCreateRequestToUserOrm userCreateRequestToUserOrm;
  private final UserUpdateRequestToUserOrm userUpdateRequestToUserOrm;
  private final UserOrmToUserResponse userOrmToUserResponse;

  public UserSource(
    UserJpaRepository userJpaRepository,
    UserCreateRequestToUserOrm userCreateRequestToUserOrm,
    UserUpdateRequestToUserOrm userUpdateRequestToUserOrm,
    UserOrmToUserResponse userOrmToUserResponse
  ) {
    this.userJpaRepository = userJpaRepository;
    this.userCreateRequestToUserOrm = userCreateRequestToUserOrm;
    this.userUpdateRequestToUserOrm = userUpdateRequestToUserOrm;
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
  public UserResponse update(Integer id, UserUpdateRequest request) {
    return userJpaRepository
      .findById(id)
      .map(user -> userUpdateRequestToUserOrm.map(request, user))
      .map(userJpaRepository::save)
      .map(userOrmToUserResponse::map)
      .orElseThrow();
  }

  @Override
  public boolean existsByDocument(String document) {
    return userJpaRepository.existsByDocument(document);
  }

  @Override
  public boolean existsByDocumentAndIdNot(String document, Integer id) {
    return userJpaRepository.existsByDocumentAndIdNot(document, id);
  }

  @Override
  public boolean existsById(Integer id) {
    return userJpaRepository.existsById(id);
  }
}
