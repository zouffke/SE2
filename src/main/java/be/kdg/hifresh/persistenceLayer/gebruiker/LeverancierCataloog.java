package be.kdg.hifresh.persistenceLayer.gebruiker;

import be.kdg.hifresh.businessLayer.domain.gebruiker.Leverancier;
import be.kdg.hifresh.persistenceLayer.Catalog;
import org.springframework.stereotype.Component;

/**
 * This class represents a catalog of suppliers.
 * It extends the Catalog class and is used to manage a collection of Leverancier objects.
 * It is annotated as a Spring component, so it can be automatically detected and instantiated by Spring.
 */
@Component
public class LeverancierCataloog extends Catalog<Leverancier> {

    /**
     * Default constructor for LeverancierCataloog.
     * Calls the parent class constructor to initialize the catalog.
     */
    public LeverancierCataloog() {
        super();
    }
}