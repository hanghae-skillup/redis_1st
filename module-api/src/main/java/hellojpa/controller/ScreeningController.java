package hellojpa.controller;

import hellojpa.dto.ScreeningDto;
import hellojpa.dto.SearchCondition;
import hellojpa.service.ScreeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ScreeningController {

    private final ScreeningService screeningService;

    @GetMapping("/screening/movies")
    public List<ScreeningDto> getCurrentScreenings(@Valid @ModelAttribute SearchCondition searchCondition) {
        log.info("ModelAttribute.title: {}", searchCondition.getTitle());
        log.info("ModelAttribute.genre: {}", searchCondition.getGenre());

        return screeningService.findCurrentScreenings(LocalDate.now(), searchCondition);
    }

}