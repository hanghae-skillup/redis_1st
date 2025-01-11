package com.hh.domain.movie;

import com.hh.domain.common.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Theater extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany
  private final List<Screen> theaterScreens = new ArrayList<>();


}
