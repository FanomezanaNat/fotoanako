package mu.mcb.fotoanako.repository;

import java.util.Optional;
import mu.mcb.fotoanako.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.agenda",
      countQuery = "SELECT count(u) FROM User u")
  Page<User> findAll( Pageable pageable);

  Optional<User> findByEmail(String email);
  Boolean  existsByEmail(String email);

}
