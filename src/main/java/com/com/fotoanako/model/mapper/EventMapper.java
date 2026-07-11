package com.com.fotoanako.model.mapper;

import com.com.fotoanako.model.Event;
import com.com.fotoanako.model.dto.EventRequest;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

  public Event toEntity(EventRequest request) {
    return Event.builder()
        .title(request.title())
        .description(request.description())
        .location(request.location())
        .startDate(request.startDate())
        .endDate(request.endDate())
        .build();
  }

}
