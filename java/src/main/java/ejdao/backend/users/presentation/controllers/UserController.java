package ejdao.backend.users.presentation.controllers;

import ejdao.backend.users.application.requests.UserCreateRequest;
import ejdao.backend.users.application.responses.UserResponse;
import ejdao.backend.users.presentation.docs.UserDocs;
import ejdao.backend.users.presentation.handlers.UserHandler;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

  @Override
  @PostMapping("/users")
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse createUser(@RequestBody UserCreateRequest request) {
    return userHandler.create(request);
  }
}
