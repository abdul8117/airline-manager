package com.abdul.airlinemanager.time;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeekController {

    private final WeekService WeekService;

    @PostConstruct
    public void init() {
        Week week = new Week();
        WeekService.createWeek(week);
    }

}
