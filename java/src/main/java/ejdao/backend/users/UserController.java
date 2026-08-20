package ejdao.backend.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Users", description = "User operations")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Operation(summary = "Get all users")
  @GetMapping("/users")
  public List<UserResponse> getUsers() {
    return userRepository.findAll().stream().map(UserResponse::from).toList();
  }
}
