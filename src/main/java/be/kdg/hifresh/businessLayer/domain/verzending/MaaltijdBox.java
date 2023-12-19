package be.kdg.hifresh.businessLayer.domain.verzending;

import be.kdg.hifresh.businessLayer.domain.util.Adres;
import be.kdg.hifresh.businessLayer.domain.util.BarCode;
import be.kdg.hifresh.businessLayer.domain.verkoop.Bestelling;

/**
 * Weekpakket met ingredienten en recepten voor een week koken.
 */
public class MaaltijdBox {

    private InpakOpdracht inpakOpdracht;
    private Bestelling bestelling;
    private Adres leverAdres;
    private BarCode trackEnTraceCode;

}