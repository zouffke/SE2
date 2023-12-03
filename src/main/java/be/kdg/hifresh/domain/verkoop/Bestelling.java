package be.kdg.hifresh.domain.verkoop;

import java.util.*;
import be.kdg.hifresh.domain.gebruiker.*;
import be.kdg.hifresh.domain.verzending.*;

/**
 * Verzoek om een product of dienst te leveren.
 */
public class Bestelling {

	private List<Maaltijd> maaltijden;
	private Klant klant;
	private InpakOpdracht inpakOpdracht;
	private AnalysisModel.be.kdg.hifresh.domain.Adres adres;
	private Double korting;

}