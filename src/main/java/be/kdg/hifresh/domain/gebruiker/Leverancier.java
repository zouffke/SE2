package be.kdg.hifresh.domain.gebruiker;

import java.util.*;

import be.kdg.hifresh.domain.aankoop.Contract;

/**
 * Bedrijf dat ingredienten levert.
 */
public class Leverancier extends Rol {

	private List<Contract> contract;
	private int reputatie;

}