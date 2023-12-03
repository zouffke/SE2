package be.kdg.hifresh.domain.recepten;

import be.kdg.hifresh.domain.verkoop.*;

import java.time.Duration;
import java.util.*;
import be.kdg.hifresh.domain.util.*;

/**
 * Instructies voor het bereiden van een gerecht.
 */
public class Recept {

	//region vars
	private Maaltijd maaltijd;
	private List<Recept> subrecepten;
	private List<Ingredient> ingredienten;
	private List<Label> labels;
	private Recept hoofdrecept;
	private List<BereidingsStap> stappen;
	private String naam;
	private Duration bereidingstijd;
	private String foto;
	private final int id;
	private String beschrijving;
	//endregion

	Recept(String naam, int id, String beschrijving){
		this.naam = naam;
		this.id = id;
		this.beschrijving = beschrijving;
	}

	int getId(){
		return this.id;
	}

	boolean addSubrecept(Recept recept){
		if (subrecepten == null) {
			subrecepten = new ArrayList<>();
		}

		return subrecepten.add(recept);
	}

	boolean addIngredient(Ingredient ingredient){
		if (this.ingredienten == null){
			this.ingredienten = new ArrayList<>();
		}

		return this.ingredienten.add(ingredient);
	}
}