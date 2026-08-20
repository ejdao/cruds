package ejdao.backend.users.infrastructure.factories;

import ejdao.backend.users.application.requests.UserUpdateRequest;
import ejdao.backend.users.infrastructure.orm.UserOrm;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateRequestToUserOrm {

  public UserOrm map(UserUpdateRequest request, UserOrm user) {
    user.setDocument(request.getDocument());
    user.setFullName(request.getFullName());
    user.setPassword(null);
    return user;
  }
}
