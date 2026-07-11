package com.com.fotoanako.repository;

import java.util.Optional;
import com.com.fotoanako.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

  Optional<Agenda> findByUserId(Long userId);

}
