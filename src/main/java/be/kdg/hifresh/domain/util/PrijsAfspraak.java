package be.kdg.hifresh.domain.util;

import lombok.Getter;

@Getter
public class PrijsAfspraak {

	private Munt prijs;
	private double maxHoeveelheid;

	PrijsAfspraak(Munt prijs, double maxHoeveelheid){
		this.prijs = prijs;
		this.maxHoeveelheid = maxHoeveelheid;
	}
}