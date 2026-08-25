package ejdao.backend.users.presentation.handlers;

import ejdao.backend.users.application.requests.UserCreateRequest;
import ejdao.backend.users.application.requests.UserUpdateRequest;
import ejdao.backend.users.application.responses.UserResponse;
import ejdao.backend.users.domain.repositories.UserRepository;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
    } catch (DataIntegrityViolationException exception) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "User document already exists.",
        exception
      );
    } catch (DataAccessException exception) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Could not create user.",
        exception
      );
    }
  }

  public UserResponse update(Integer id, UserUpdateRequest request) {
    validateUpdateRequest(id, request);

    try {
      validateExistingUser(id);
      validateUniqueDocumentForUpdate(request.getDocument(), id);
      return userRepository.update(id, request);
    } catch (DataIntegrityViolationException exception) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "User document already exists.",
        exception
      );
    } catch (DataAccessException exception) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Could not update user.",
        exception
      );
    }
  }

  public void delete(Integer id) {
    validateDeleteRequest(id);

    try {
      validateExistingUser(id);
      userRepository.delete(id);
    } catch (DataAccessException exception) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Could not delete user.",
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

  private void validateUniqueDocumentForUpdate(String document, Integer id) {
    if (userRepository.existsByDocumentAndIdNot(document, id)) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "User document already exists."
      );
    }
  }

  private void validateExistingUser(Integer id) {
    if (!userRepository.existsById(id)) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "User not found."
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
  }

  private void validateUpdateRequest(Integer id, UserUpdateRequest request) {
    if (id == null) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User id is required."
      );
    }

    if (request == null) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User body is required."
      );
    }
  }

  private void validateDeleteRequest(Integer id) {
    if (id == null) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User id is required."
      );
    }
  }
}
