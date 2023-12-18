package be.kdg.hifresh.applicationLayer.gebruiker;

import be.kdg.hifresh.businessLayer.gebruiker.GebruikerFactory;
import be.kdg.hifresh.businessLayer.gebruiker.Leverancier;
import be.kdg.hifresh.persistenceLayer.gebruiker.GebruikerManager;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;

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
        return manager.add(GebruikerFactory.createLeverancier(id, name), manager.getLeverancierCatalog());
    }
    //endregion

    public static Leverancier getLeverancier(int leverancierId) throws InvocationTargetException, IllegalAccessException {
        return manager.getById(leverancierId, manager.getLeverancierCatalog());
    }
}
