package ejdao.backend.users.application.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateRequest {

  private final String document;
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
