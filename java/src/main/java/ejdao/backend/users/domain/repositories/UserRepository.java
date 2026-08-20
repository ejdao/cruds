package ejdao.backend.users.domain.repositories;

import ejdao.backend.users.application.requests.UserCreateRequest;
import ejdao.backend.users.application.requests.UserUpdateRequest;
import ejdao.backend.users.application.responses.UserResponse;
import java.util.List;

public interface UserRepository {
  List<UserResponse> fetch();
  UserResponse create(UserCreateRequest request);
  UserResponse update(Integer id, UserUpdateRequest request);
  void delete(Integer id);
  boolean existsByDocument(String document);
  boolean existsByDocumentAndIdNot(String document, Integer id);
  boolean existsById(Integer id);
}
