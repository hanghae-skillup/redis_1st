package com.movie.app.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class TicketingRequestDto {
    private List<Integer> seats;
}
