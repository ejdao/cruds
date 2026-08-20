package ejdao.backend.users.infrastructure.factories;

import ejdao.backend.users.application.requests.UserCreateRequest;
import ejdao.backend.users.infrastructure.orm.UserOrm;
import org.springframework.stereotype.Component;

@Component
public class UserCreateRequestToUserOrm {

  public UserOrm map(UserCreateRequest request) {
    UserOrm user = new UserOrm();
    user.setDocument(request.getDocument());
    user.setFullName(request.getFullName());
    user.setPassword(null);
    return user;
  }
}
