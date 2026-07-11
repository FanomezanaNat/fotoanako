package com.com.fotoanako.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import com.com.fotoanako.model.Agenda;
import com.com.fotoanako.model.dto.AgendaRequest;
import com.com.fotoanako.model.dto.AgendaResponse;
import com.com.fotoanako.model.mapper.AgendaMapper;
import com.com.fotoanako.model.mapper.EventMapper;
import com.com.fotoanako.repository.AgendaRepository;
import com.com.fotoanako.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgendaService {

  private final AgendaRepository agendaRepository;
  private final UserRepository userRepository;
  private final AgendaMapper agendaMapper;
  private final EventMapper eventMapper;

  public AgendaService(AgendaRepository agendaRepository, UserRepository userRepository,
      AgendaMapper agendaMapper, EventMapper eventMapper) {
    this.agendaRepository = agendaRepository;
    this.userRepository = userRepository;
    this.agendaMapper = agendaMapper;
    this.eventMapper = eventMapper;
  }

  public AgendaResponse create(Authentication authenticated, AgendaRequest agendaRequest) {
    var email = authenticated.getName();
    var user = userRepository.findByEmail(email)
        .orElseThrow();

    var agenda = Agenda.builder()
        .user(user)
        .events(new ArrayList<>())
        .build();

    if (agendaRequest.events() != null) {
      var events = agendaRequest.events()
          .stream()
          .map(eventMapper::toEntity)
          .peek(event -> event.setAgenda(agenda))
          .toList();

      agenda.getEvents()
          .addAll(events);
    }

    agendaRepository.save(agenda);
    return agendaMapper.toResponse(agenda);
  }

  @Transactional(readOnly = true)
  public Page<AgendaResponse> findAll(Pageable pageable) {

    return agendaRepository.findAll(pageable)
        .map(agendaMapper::toResponse);

  }

  @Transactional(readOnly = true)
  public AgendaResponse getUserAgenda(Authentication authenticated) {
    var email = authenticated.getName();
    var user = userRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));

    var agenda = agendaRepository.findByUserId(user.getId())
        .orElseThrow(
            () -> new EntityNotFoundException(
                "Agenda Not Found with userId: %d".formatted(user.getId())));

    return agendaMapper.toResponse(agenda);
  }

  public void delete(Authentication authenticated) {
    var email = authenticated.getName();
    var user = userRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));

    var agenda = agendaRepository.findByUserId(user.getId())
        .orElseThrow(
            () -> new EntityNotFoundException(
                "Agenda Not Found with userId: %d".formatted(user.getId())));

    agendaRepository.delete(agenda);
  }


}
