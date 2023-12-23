package be.kdg.hifresh.businessLayer.util;

import java.time.LocalDate;

/**
 * Represents a period of time between two dates.
 */
public class Periode {

    /**
     * The start date of the period.
     */
    private final LocalDate START;

    /**
     * The end date of the period.
     */
    private final LocalDate END;

    /**
     * Constructor for Periode.
     *
     * @param start The start date of the period.
     * @param end   The end date of the period.
     */
    Periode(LocalDate start, LocalDate end) {
        this.START = start;
        this.END = end;
    }

    /**
     * Checks if a given date is within the period.
     *
     * @param date The date to be checked.
     * @return true if the date is within the period, false otherwise.
     */
    public boolean isIn(LocalDate date) {
        return START.isEqual(date) || END.isEqual(date) || (START.isBefore(date) && END.isAfter(date));
    }
}