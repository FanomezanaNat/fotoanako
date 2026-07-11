package com.com.fotoanako.model.mapper;

import com.com.fotoanako.model.Agenda;
import com.com.fotoanako.model.dto.AgendaResponse;
import org.springframework.stereotype.Component;

@Component
public class AgendaMapper {

  public AgendaResponse toResponse(Agenda agenda) {
    return AgendaResponse.builder()
        .id(agenda.getId())
        .user(agenda.getUser())
        .events(agenda.getEvents())
        .build();
  }

}
