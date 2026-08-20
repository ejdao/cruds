package ejdao.backend.users;

public record UserResponse(Integer id, String document, String fullName) {
  public static UserResponse from(User user) {
    return new UserResponse(
      user.getId(),
      user.getDocument(),
      user.getFullName()
    );
  }
}
