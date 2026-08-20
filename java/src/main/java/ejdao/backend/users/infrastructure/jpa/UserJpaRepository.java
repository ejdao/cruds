package ejdao.backend.users.infrastructure.jpa;

import ejdao.backend.users.infrastructure.orm.UserOrm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserOrm, Integer> {

  boolean existsByDocument(String document);
}
