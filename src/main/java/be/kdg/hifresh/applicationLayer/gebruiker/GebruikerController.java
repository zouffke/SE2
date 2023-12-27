package be.kdg.hifresh.applicationLayer.gebruiker;

import be.kdg.hifresh.businessLayer.domain.gebruiker.GebruikerFactory;
import be.kdg.hifresh.businessLayer.domain.gebruiker.Leverancier;
import be.kdg.hifresh.businessLayer.services.Manager;
import be.kdg.hifresh.businessLayer.services.gebruiker.GebruikerManager;
import be.kdg.hifresh.persistenceLayer.gebruiker.LeverancierCataloog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * This class is responsible for managing users.
 * It provides methods to add suppliers, get suppliers and clear catalogs.
 */
@Controller
public final class GebruikerController {
    //region vars

    /**
     * Manager instance for managing users
     */
    private final Manager MANAGER;

    //endregion

    //region constructors

    /**
     * Private constructor to prevent instantiation
     * @param manager Manager instance
     */
    @Autowired
    public GebruikerController(GebruikerManager manager) {
        this.MANAGER = manager;
    }

    //endregion

    //region setup functions

    /**
     * Adds a supplier to the catalog
     *
     * @param id Supplier ID
     * @param name Supplier name
     * @return true if the supplier was added successfully, false otherwise
     */
    public boolean addLeverancier(int id, String name) {
        return MANAGER.add(GebruikerFactory.createLeverancier(id, name), MANAGER.getCatalog(LeverancierCataloog.class));
    }

    //endregion

    /**
     * Retrieves a supplier from the catalog by its ID
     *
     * @param leverancierId Supplier ID
     * @return Supplier object
     */
    public Leverancier getLeverancier(int leverancierId) {
        return MANAGER.getById(leverancierId, MANAGER.getCatalog(LeverancierCataloog.class));
    }

    /**
     * Clears all catalogs
     */
    public void clearCatalogs() {
        this.MANAGER.clearCatalogs();
    }
}