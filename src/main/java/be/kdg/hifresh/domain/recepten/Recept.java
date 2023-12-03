package be.kdg.hifresh.domain.recepten;

import be.kdg.hifresh.domain.verkoop.*;

import java.time.Duration;
import java.util.*;
import be.kdg.hifresh.domain.util.*;

/**
 * Instructies voor het bereiden van een gerecht.
 */
public class Recept {

	private Maaltijd maaltijd;
	private List<Recept> subrecepten;
	private List<Ingredient> ingredienten;
	private List<Label> labels;
	private Recept hoofdrecept;
	private List<BereidingsStap> stappen;
	private String naam;
	private Duration bereidingstijd;
	private String foto;

}