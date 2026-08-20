package ejdao.backend.users.presentation.handlers;

import ejdao.backend.users.application.requests.UserCreateRequest;
import ejdao.backend.users.application.responses.UserResponse;
import ejdao.backend.users.domain.repositories.UserRepository;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserHandler {

  private final UserRepository userRepository;

  public UserHandler(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserResponse> fetch() {
    try {
      return userRepository.fetch();
    } catch (DataAccessException exception) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Could not fetch users.",
        exception
      );
    }
  }

  public UserResponse create(UserCreateRequest request) {
    validateCreateRequest(request);

    try {
      validateUniqueDocument(request.getDocument());
      return userRepository.create(request);
    } catch (DataAccessException exception) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Could not create user.",
        exception
      );
    }
  }

  private void validateUniqueDocument(String document) {
    if (userRepository.existsByDocument(document)) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "User document already exists."
      );
    }
  }

  private void validateCreateRequest(UserCreateRequest request) {
    if (request == null) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User body is required."
      );
    }

    if (request.getDocument() == null || request.getDocument().isBlank()) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User document is required."
      );
    }

    if (request.getFullName() == null || request.getFullName().isBlank()) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User full name is required."
      );
    }
  }
}
