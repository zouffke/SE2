package be.kdg.hifresh.domain.aankoop;

import java.util.*;
import be.kdg.hifresh.domain.verzending.*;
import be.kdg.hifresh.domain.util.*;

/**
 * Locatie waar de FreshBoxen worden samengesteld.
 */
public class DistributieCentrum {

	private List<Levering> leveringen;
	private List<MaaltijdBox> maaltijdBoxen;
	private Adres adres;
	private final int id;
	private String name;

	DistributieCentrum(int id, String name){
		this.id = id;
		this.name = name;
	}

}