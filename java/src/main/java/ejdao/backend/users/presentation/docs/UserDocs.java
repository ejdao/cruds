package ejdao.backend.users.presentation.docs;

import ejdao.backend.users.application.requests.UserCreateRequest;
import ejdao.backend.users.application.requests.UserUpdateRequest;
import ejdao.backend.users.application.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Users", description = "User operations")
public interface UserDocs {
  @Operation(summary = "Get all users")
  List<UserResponse> getUsers();

  @Operation(
    summary = "Create a user",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "User created successfully"
      ),
      @ApiResponse(responseCode = "400", description = "Invalid user data"),
      @ApiResponse(
        responseCode = "409",
        description = "User document already exists"
      ),
      @ApiResponse(responseCode = "500", description = "Could not create user"),
    }
  )
  UserResponse createUser(UserCreateRequest request);

  @Operation(
    summary = "Update a user",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "User updated successfully"
      ),
      @ApiResponse(responseCode = "400", description = "Invalid user data"),
      @ApiResponse(responseCode = "404", description = "User not found"),
      @ApiResponse(
        responseCode = "409",
        description = "User document already exists"
      ),
      @ApiResponse(responseCode = "500", description = "Could not update user"),
    }
  )
  UserResponse updateUser(Integer id, UserUpdateRequest request);

  @Operation(
    summary = "Delete a user",
    responses = {
      @ApiResponse(
        responseCode = "204",
        description = "User deleted successfully"
      ),
      @ApiResponse(responseCode = "400", description = "Invalid user id"),
      @ApiResponse(responseCode = "404", description = "User not found"),
      @ApiResponse(responseCode = "500", description = "Could not delete user"),
    }
  )
  void deleteUser(Integer id);
}
