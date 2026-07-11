package com.com.fotoanako.model.dto;

import java.time.LocalDateTime;

public record EventRequest(
    String title,
    String description,
    String location,
    LocalDateTime startDate,
    LocalDateTime endDate
) {

}
