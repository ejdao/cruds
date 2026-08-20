package ejdao.backend.users.domain.repositories;

import ejdao.backend.users.application.responses.UserResponse;
import java.util.List;

public interface UserRepository {
  List<UserResponse> fetch();
}
