package ejdao.backend.users.application.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class UserUpdateRequest {

  @NotBlank(message = "User document is required.")
  private final String document;

  @NotBlank(message = "User full name is required.")
  private final String fullName;

  @JsonCreator
  public UserUpdateRequest(
    @JsonProperty("document") String document,
    @JsonProperty("fullName") String fullName
  ) {
    this.document = document;
    this.fullName = fullName;
  }

  public String getDocument() {
    return document;
  }

  public String getFullName() {
    return fullName;
  }
}
