package ejdao.backend.users.infrastructure.factories;

import ejdao.backend.users.application.responses.UserResponse;
import ejdao.backend.users.infrastructure.orm.UserOrm;
import org.springframework.stereotype.Component;

@Component
public class UserOrmToUserResponse {

  public UserResponse map(UserOrm user) {
    return new UserResponse(user.getId(), user.getDocument(), user.getFullName());
  }
}
