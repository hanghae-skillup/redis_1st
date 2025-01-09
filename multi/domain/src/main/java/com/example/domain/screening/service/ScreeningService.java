package com.example.domain.screening.service;

import com.example.domain.screening.Screening;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScreeningService {
    private  final ScreeningRepository screeningRepository;

    public Screening getScreeningInfo(Long screeningId) {
        return screeningRepository.getScreeningInfo(screeningId);
    }

    public List<Screening> getAllScreeningInfo(){
        return screeningRepository.getAllScreeningInfo();
    }

   public void enrollScreening(Screening screening){
        screeningRepository.saveScreening(screening);
   }
}
