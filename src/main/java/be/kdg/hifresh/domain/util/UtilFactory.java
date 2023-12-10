package be.kdg.hifresh.domain.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UtilFactory {
    private UtilFactory() {
    }

    public static Periode createPeriod(LocalDate start, LocalDate end) {
        return new Periode(start, end);
    }

    public static PrijsAfspraak createPrijsAfspraak(Munt prijs, double maxHoeveelheid){
        return new PrijsAfspraak(prijs, maxHoeveelheid);
    }

    public static Munt createMunt(double bedrag, String eenheid){
        return new Munt(bedrag, eenheid);
    }
}
