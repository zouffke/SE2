package be.kdg.hifresh.domain.util;

public class PrijsAfspraak {

	private Munt prijs;
	private double maxHoeveelheid;

	PrijsAfspraak(Munt prijs, double maxHoeveelheid){
		this.prijs = prijs;
		this.maxHoeveelheid = maxHoeveelheid;
	}
}