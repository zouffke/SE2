package be.kdg.hifresh.businessLayer.services.gebruiker;

import be.kdg.hifresh.businessLayer.domain.gebruiker.Leverancier;
import be.kdg.hifresh.persistenceLayer.Catalog;
import be.kdg.hifresh.businessLayer.services.Manager;
import be.kdg.hifresh.persistenceLayer.gebruiker.LeverancierCataloog;
import lombok.Getter;

@Getter
public class GebruikerManager extends Manager {
    //region vars
    private final Catalog<Leverancier> LEVERANCIER_CATALOG;
    //endregion

    //region constructors
    public GebruikerManager() {
        this.LEVERANCIER_CATALOG = new LeverancierCataloog();
    }
    //endregion
}
