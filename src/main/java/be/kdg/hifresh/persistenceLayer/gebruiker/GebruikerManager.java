package be.kdg.hifresh.persistenceLayer.gebruiker;

import be.kdg.hifresh.businessLayer.gebruiker.Leverancier;
import be.kdg.hifresh.persistenceLayer.Catalog;
import be.kdg.hifresh.persistenceLayer.Manager;
import be.kdg.hifresh.persistenceLayer.gebruiker.catalogs.LeverancierCataloog;
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
