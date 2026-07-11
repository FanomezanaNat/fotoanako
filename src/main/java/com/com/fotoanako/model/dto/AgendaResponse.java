package com.com.fotoanako.model.dto;

import java.util.List;
import lombok.Builder;
import com.com.fotoanako.model.Event;
import com.com.fotoanako.model.User;

@Builder
public record AgendaResponse(Long id, User user, List<Event> events) {

}
