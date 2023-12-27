package be.kdg.hifresh.persistenceLayer.aankoop;

import be.kdg.hifresh.businessLayer.domain.aankoop.Contract;
import be.kdg.hifresh.persistenceLayer.Catalog;
import org.springframework.stereotype.Component;

/**
 * A catalog class for managing Contract objects.
 *
 * @author Dandois Luca
 */
@Component
public class ContractCataloog extends Catalog<Contract> {

    /**
     * Constructor for ContractCataloog.
     *
     * @author Dandois Luca
     */
    public ContractCataloog() {
        super();
    }
}