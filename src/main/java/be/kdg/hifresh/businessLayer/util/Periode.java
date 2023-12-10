package be.kdg.hifresh.businessLayer.util;

import java.time.LocalDate;

/**
 * Represents a period of time between two dates.
 */
public class Periode {

    /**
     * The start date of the period.
     */
    private LocalDate start;

    /**
     * The end date of the period.
     */
    private LocalDate end;

    /**
     * Constructor for Periode.
     *
     * @param start The start date of the period.
     * @param end The end date of the period.
     */
    Periode(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Checks if a given date is within the period.
     *
     * @param date The date to be checked.
     * @return true if the date is within the period, false otherwise.
     */
    public boolean isIn(LocalDate date) {
        return start.isEqual(date) || end.isEqual(date) || (start.isBefore(date) && end.isAfter(date));
    }
}