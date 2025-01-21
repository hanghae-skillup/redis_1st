package com.movie.domain.config;

import com.movie.domain.movie.dto.command.ScheduleCommand;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("scheduleKeyGenerator")
public class ScheduleKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        if (params.length > 0 && params[0] instanceof ScheduleCommand.Search search) {
            String title = search.title() != null ? search.title() : "영화";
            String genre = search.genre() != null ? search.genre().name() : "NONE";
            return title + "_" + genre;
        }
        return "";
    }
}
