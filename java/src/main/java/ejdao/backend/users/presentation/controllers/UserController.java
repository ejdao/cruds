package ejdao.backend.users.presentation.controllers;

import ejdao.backend.users.application.responses.UserResponse;
import ejdao.backend.users.presentation.docs.UserDocs;
import ejdao.backend.users.presentation.handlers.UserHandler;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserDocs {

  private final UserHandler userHandler;

  public UserController(UserHandler userHandler) {
    this.userHandler = userHandler;
  }

  @Override
  @GetMapping("/users")
  public List<UserResponse> getUsers() {
    return userHandler.fetch();
  }
}
