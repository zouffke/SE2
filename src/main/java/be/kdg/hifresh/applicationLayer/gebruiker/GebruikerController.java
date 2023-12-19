package be.kdg.hifresh.applicationLayer.gebruiker;

import be.kdg.hifresh.businessLayer.domain.gebruiker.GebruikerFactory;
import be.kdg.hifresh.businessLayer.domain.gebruiker.Leverancier;
import be.kdg.hifresh.businessLayer.services.gebruiker.GebruikerManager;
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
        return manager.add(GebruikerFactory.createLeverancier(id, name), manager.getLEVERANCIER_CATALOG());
    }
    //endregion

    public static Leverancier getLeverancier(int leverancierId) throws InvocationTargetException, IllegalAccessException {
        return manager.getById(leverancierId, manager.getLEVERANCIER_CATALOG());
    }
}
