package be.kdg.hifresh.domain.util;

import java.time.LocalDate;

public class Periode {

    private LocalDate start;
    private LocalDate end;

    Periode(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public boolean isIn(LocalDate date) {
        return start.isEqual(date) || end.isEqual(date) || (start.isBefore(date) && end.isAfter(date));
    }
}