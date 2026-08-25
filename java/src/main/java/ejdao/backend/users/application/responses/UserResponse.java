package ejdao.backend.users.application.responses;

public class UserResponse {

  private final Integer id;
  private final String document;
  private final String fullName;

  public UserResponse(Integer id, String document, String fullName) {
    this.id = id;
    this.document = document;
    this.fullName = fullName;
  }

  public Integer getId() {
    return id;
  }

  public String getDocument() {
    return document;
  }

  public String getFullName() {
    return fullName;
  }
}
