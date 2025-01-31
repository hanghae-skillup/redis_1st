package com.movie.movieapi.interfaces.movie;

import com.movie.movieapi.interfaces.movie.dto.ScheduleDto;
import com.movie.common.response.Response;
import com.movie.domain.movie.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<ScheduleDto.Response>> getSchedules(@RequestBody ScheduleDto.Search searchDto) {
        List<ScheduleDto.Response> responses = scheduleService.getSchedules(searchDto.search()).stream()
                .map(ScheduleDto.Response::from)
                .toList();
        return Response.success(responses);
    }

    @PostMapping(value = "/cached", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<ScheduleDto.Response>> getSchedulesAsCached(@RequestBody ScheduleDto.Search searchDto) {
        List<ScheduleDto.Response> responses = scheduleService.getSchedulesAsCached(searchDto.search()).stream()
                .map(ScheduleDto.Response::from)
                .toList();
        return Response.success(responses);
    }

    @PostMapping(value = "/redis", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<ScheduleDto.Response>> getSchedulesByRedis(@RequestBody ScheduleDto.Search searchDto) {
        List<ScheduleDto.Response> responses = scheduleService.getSchedulesByRedis(searchDto.search()).stream()
                .map(ScheduleDto.Response::from)
                .toList();
        return Response.success(responses);
    }

}
