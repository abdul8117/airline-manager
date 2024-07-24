package com.abdul.airlinemanager.time;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeekService {

    private final WeekRepository weekRepository;

    public void createWeek(Week week) {
        weekRepository.save(week);
    }

    public Week getCurrentWeek() {
        return weekRepository.findAll().getFirst();
    }

}
