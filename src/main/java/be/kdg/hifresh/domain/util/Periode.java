package be.kdg.hifresh.domain.util;

import java.time.LocalDateTime;

public class Periode {

    private LocalDateTime start;
    private LocalDateTime end;

    Periode(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }
}