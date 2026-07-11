package com.com.fotoanako.repository;

import java.util.Optional;
import com.com.fotoanako.model.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.agenda",
      countQuery = "SELECT count(u) FROM User u")
  Page<User> findAll(@NonNull Pageable pageable);

  Optional<User> findByEmail(String email);

  Boolean existsByEmail(String email);

}
