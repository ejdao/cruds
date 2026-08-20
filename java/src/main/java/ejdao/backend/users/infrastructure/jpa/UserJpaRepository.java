package ejdao.backend.users.infrastructure.jpa;

import ejdao.backend.users.infrastructure.orm.UserOrm;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserOrm, Integer> {
  List<UserOrm> findAllByDeletedAtIsNull();
  Optional<UserOrm> findByIdAndDeletedAtIsNull(Integer id);
  boolean existsByDocumentAndDeletedAtIsNull(String document);
  boolean existsByDocumentAndIdNotAndDeletedAtIsNull(
    String document,
    Integer id
  );
  boolean existsByIdAndDeletedAtIsNull(Integer id);
}
