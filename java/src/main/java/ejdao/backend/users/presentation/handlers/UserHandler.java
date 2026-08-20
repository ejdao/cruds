package ejdao.backend.users.presentation.handlers;

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
}
