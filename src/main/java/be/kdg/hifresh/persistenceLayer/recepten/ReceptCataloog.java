package be.kdg.hifresh.persistenceLayer.recepten;

import be.kdg.hifresh.businessLayer.domain.recepten.Recept;
import be.kdg.hifresh.persistenceLayer.Catalog;
import org.springframework.stereotype.Component;

/**
 * A catalog class for managing Recept objects.
 *
 * @author Dandois Luca
 */
@Component
public class ReceptCataloog extends Catalog<Recept> {

    /**
     * Constructor for ReceptCataloog.
     *
     * @author Dandois Luca
     */
    public ReceptCataloog() {
        super();
    }
}