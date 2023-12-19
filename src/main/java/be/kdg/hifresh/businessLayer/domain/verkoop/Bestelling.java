package be.kdg.hifresh.businessLayer.domain.verkoop;

import be.kdg.hifresh.businessLayer.domain.gebruiker.Klant;
import be.kdg.hifresh.businessLayer.domain.util.Adres;
import be.kdg.hifresh.businessLayer.domain.verzending.InpakOpdracht;

import java.util.List;

/**
 * Verzoek om een product of dienst te leveren.
 */
public class Bestelling {

    private List<Maaltijd> maaltijden;
    private Klant klant;
    private InpakOpdracht inpakOpdracht;
    private Adres adres;
    private Double korting;

}