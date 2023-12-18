package be.kdg.hifresh.businessLayer.verkoop;

import be.kdg.hifresh.businessLayer.gebruiker.Klant;
import be.kdg.hifresh.businessLayer.util.Adres;
import be.kdg.hifresh.businessLayer.verzending.InpakOpdracht;

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