package com.hh.domain.movie;

import com.hh.domain.common.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Screen extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private int movieId;
  
  private int theaterId;

  protected LocalDateTime startTime;

  private LocalDateTime endTime;



}
