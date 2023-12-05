package be.kdg.hifresh.domain.aankoop;

import java.util.*;

public class DistributieCentraCataloog {

	private List<DistributieCentrum> distributieCentra;

	DistributieCentraCataloog(){
		this.distributieCentra = new ArrayList<>();
	}

	boolean addCentrum(DistributieCentrum distributieCentrum){
		return this.distributieCentra.add(distributieCentrum);
	}

	DistributieCentrum createNewCentrum(int id, String name){
		return new DistributieCentrum(id, name);
	}
}