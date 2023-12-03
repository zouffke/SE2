package be.kdg.hifresh.domain.aankoop;

/**
 * Startup die ingredienten en recepten aan huis levert voor een week lekker zelf koken.
 */
public class ContractManager {

	private final ContractCataloog contractCataloog;
	private final DistributieCentraCataloog dcCataloog;

	public ContractManager(){
		this.contractCataloog = new ContractCataloog();
		this.dcCataloog = new DistributieCentraCataloog();
	}
}