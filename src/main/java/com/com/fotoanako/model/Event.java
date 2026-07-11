package com.com.fotoanako.model;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String title;
  private String description;
  private String location;
  private LocalDateTime startDate;
  private LocalDateTime endDate;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "agenda_id", nullable = false)
  private Agenda agenda;

}
