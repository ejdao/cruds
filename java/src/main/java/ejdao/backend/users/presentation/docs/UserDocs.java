package ejdao.backend.users.presentation.docs;

import ejdao.backend.users.application.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Users", description = "User operations")
public interface UserDocs {

  @Operation(summary = "Get all users")
  List<UserResponse> getUsers();
}
