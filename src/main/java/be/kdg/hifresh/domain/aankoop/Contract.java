package be.kdg.hifresh.domain.aankoop;

import be.kdg.hifresh.domain.gebruiker.*;
import java.util.*;

/**
 * Een overeenkomst tussen HiFresh en een leverancier over de levering van producten.
 */
public class Contract {

	private Leverancier leverancier;
	private Product product;
	private List<Clausule> clausules;

	Contract(Product product){
		this.product = product;
	}

	Product getProduct(){
		return this.product;
	}

	boolean addClausule(Clausule clausule){
		if (this.clausules == null){
			this.clausules = new ArrayList<>();
		}

		return this.clausules.add(clausule);
	}
}