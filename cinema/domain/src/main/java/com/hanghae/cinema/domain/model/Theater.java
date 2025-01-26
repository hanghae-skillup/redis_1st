package com.hanghae.cinema.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Theater {
    private Long id;
    private String name;
}
