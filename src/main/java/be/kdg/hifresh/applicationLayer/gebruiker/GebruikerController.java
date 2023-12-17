package be.kdg.hifresh.applicationLayer.gebruiker;

import be.kdg.hifresh.businessLayer.gebruiker.GebruikerFactory;
import be.kdg.hifresh.persistenceLayer.gebruiker.GebruikerManager;
import lombok.Setter;

public final class GebruikerController {
    //region vars
    @Setter
    private static GebruikerManager manager;

    //endregion
    //constructors
    private GebruikerController() {
    }
    //endregion

    //region setup functions
    public static boolean addLeverancier(int id, String name) {
        return manager.addObjtoCatalog(GebruikerFactory.createLeverancier(id, name), manager.getLeverancierCatalog());
    }
    //endregion
}
