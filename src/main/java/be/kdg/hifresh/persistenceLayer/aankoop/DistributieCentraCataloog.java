package be.kdg.hifresh.persistenceLayer.aankoop;

import be.kdg.hifresh.businessLayer.domain.aankoop.DistributieCentrum;
import be.kdg.hifresh.persistenceLayer.Catalog;
import org.springframework.stereotype.Component;

/**
 * A catalog class for managing DistributieCentrum objects.
 *
 * @author Dandois Luca
 */
@Component
public class DistributieCentraCataloog extends Catalog<DistributieCentrum> {

    /**
     * Constructor for DistributieCentraCataloog.
     *
     * @author Dandois Luca
     */
    public DistributieCentraCataloog() {
        super();
    }
}