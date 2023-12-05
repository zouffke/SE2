package be.kdg.hifresh.domain.aankoop;

import java.util.*;

/**
 * Een goed dat geleverd wordt door een leverancier en dat nodig is voor het bereiden van een maaltijd.
 */
public class Product {

	private List<Contract> contracten;
	private String naam;
	private final int id;

	Product(String naam, int id){
		this.naam = naam;
		this.id = id;
	}

	public int getId(){
		return this.id;
	}
}