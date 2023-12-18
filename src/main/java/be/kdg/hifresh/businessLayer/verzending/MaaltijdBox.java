package be.kdg.hifresh.businessLayer.verzending;

import be.kdg.hifresh.businessLayer.util.Adres;
import be.kdg.hifresh.businessLayer.util.BarCode;
import be.kdg.hifresh.businessLayer.verkoop.Bestelling;

/**
 * Weekpakket met ingredienten en recepten voor een week koken.
 */
public class MaaltijdBox {

    private InpakOpdracht inpakOpdracht;
    private Bestelling bestelling;
    private Adres leverAdres;
    private BarCode trackEnTraceCode;

}