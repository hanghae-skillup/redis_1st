package com.movie.movieapi.interfaces.movie;

import com.movie.movieapi.interfaces.movie.dto.ScheduleDto;
import com.movie.domain.response.Response;
import com.movie.domain.movie.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public Response<List<ScheduleDto.Response>> getSchedules(@RequestParam("theaterId") Long theaterId) {
        List<ScheduleDto.Response> responses = scheduleService.getSchedules(theaterId).stream()
                .map(ScheduleDto.Response::from)
                .toList();
        return Response.success(responses);
    }

    @PostMapping
    public Response<List<ScheduleDto.Response>> getSchedules(@RequestBody ScheduleDto.Search searchDto) {
        List<ScheduleDto.Response> responses = scheduleService.getSchedules(searchDto.search()).stream()
                .map(ScheduleDto.Response::from)
                .toList();
        return Response.success(responses);
    }

}
