package hellojpa.controller;

import hellojpa.dto.ScreeningDto;
import hellojpa.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @GetMapping("/screening/movies")
    public List<ScreeningDto> getCurrentScreenings() {
        return screeningService.findCurrentScreenings();
    }
}