package be.kdg.hifresh.businessLayer.domain.gebruiker;

import be.kdg.hifresh.businessLayer.domain.verkoop.Bestelling;
import java.util.List;

/**
 * This class represents a customer in the system.
 * It extends the Role class and contains a list of orders associated with the customer.
 */
@SuppressWarnings("unused")
public class Klant extends Rol {

    /**
     * List of orders associated with the customer.
     */
    @SuppressWarnings("unused")
    private List<Bestelling> bestellingen;

}