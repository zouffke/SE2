package be.kdg.hifresh.domain.aankoop;

import java.util.*;
import be.kdg.hifresh.domain.util.*;

public class Clausule {

	private Contract contract;
	private List<Levering> leveringen;
	private List<InkoopOrder> inkoopOrders;
	private Periode periode;
	private Eenheid eenheid;
	private PrijsAfspraak prijsAfspraak;
	private final int id;
	private double hoeveelheid;

	Clausule(int id, Contract contract, Periode periode, double hoeveelheid, Eenheid eenheid, PrijsAfspraak prijsAfspraak){
		this.id = id;
		this.contract = contract;
		this.leveringen = new ArrayList<>();
		this.periode = periode;
		this.hoeveelheid = hoeveelheid;
		this.eenheid = eenheid;
		this.prijsAfspraak = prijsAfspraak;
	}

	boolean addLevering(Levering levering){
		return this.leveringen.add(levering);
	}

}